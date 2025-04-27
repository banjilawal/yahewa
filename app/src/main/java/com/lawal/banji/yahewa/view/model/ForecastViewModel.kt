package com.lawal.banji.yahewa.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.repo.ForecastRepository
import com.lawal.banji.yahewa.repo.QueryResult
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.getRandomCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForecastViewModel(private val repository: ForecastRepository) : ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> get() = _forecastState

    private val _cityLookupState = MutableStateFlow<CityLookupState>(CityLookupState.Loading)
    val city: StateFlow<CityLookupState> get() = _cityLookupState

    init {
        viewModelScope.launch {
            val location = getRandomCity()
            println("Location: ${location.name} (${location.latitude}, ${location.longitude})")
            fetchForecastByCoordinates(
                latitude = location.latitude, //AppDefault.LATITUDE,
                longitude = location.longitude, //AppDefault.LONGITUDE,
                apiKey = AppDefault.API_KEY
            )
        }
    }

    private fun fetchForecastByCoordinates(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            when (val queryResult = repository.fetchByCoordinates(
                latitude = latitude,
                longitude = longitude,
                apiKey = apiKey
            )) {
                is QueryResult.Success -> _forecastState.value =
                    ForecastState.Success(queryResult.data)

                is QueryResult.Error -> _forecastState.value =
                    ForecastState.Error("Error: ${queryResult.exception.message}")
            }
        }
    }

    fun fetchCity(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            _cityLookupState.value = CityLookupState.Loading // Set loading state for city lookup
            when (val queryResult = repository.fetchReverseGeoCoding(latitude, longitude, apiKey)) {
                is QueryResult.Success -> {
                    _cityLookupState.value =
                        CityLookupState.Success(queryResult.data) // Set success state
                }

                is QueryResult.Error -> {
                    _cityLookupState.value =
                        CityLookupState.Error("Error fetching city: ${queryResult.exception.message}")
                }
            }
        }
    }

    private fun updateStateAndCountry(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            fetchCity(latitude, longitude, apiKey) // Fetch the city

            // If forecast has already been fetched and is in success state
            if (_forecastState.value is ForecastState.Success) {
                val forecast = (_forecastState.value as ForecastState.Success).forecast

                // Check city lookup state for success
                if (_cityLookupState.value is CityLookupState.Success) {
                    val city = (_cityLookupState.value as CityLookupState.Success).city

                    // Update `forecast.state` if `city.state` is not null
                    city.state?.let {
                        forecast.state = it
                    }

                    // Update `forecast.country` if `city.country` is not null
                    city.country?.let {
                        forecast.country = it
                    }

                    // Push updated state back to forecast
                    _forecastState.value = ForecastState.Success(forecast)
                }
            }
        }
    }
}
