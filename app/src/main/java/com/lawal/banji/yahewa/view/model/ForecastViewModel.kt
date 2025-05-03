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
    val cityLookupState: StateFlow<CityLookupState> get() = _cityLookupState

    init {
        viewModelScope.launch {
            val location = getRandomCity()
            println("Location: ${location.name} (${location.latitude}, ${location.longitude})")

            // Fetch the forecast
            fetchForecastByCoordinates(
                latitude = location.latitude,
                longitude = location.longitude,
                apiKey = AppDefault.API_KEY
            )
//
//            // Fetch the city
//            fetchCity(
//                latitude = location.latitude,
//                longitude = location.longitude,
//                apiKey = AppDefault.API_KEY
//            )
//
//            // Update the forecast state with city data
//            updateForecastWithCityState()
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
            println("FETCHING CITY")
            _cityLookupState.value = CityLookupState.Loading // Set loading state for city lookup
            when (val queryResult = repository.fetchReverseGeoCoding(latitude, longitude, apiKey)) {
                is QueryResult.Success -> {
                    _cityLookupState.value =
                        CityLookupState.Success(queryResult.data) // Set success state
                    println("City Lookup Success: ${queryResult.data.name} (${queryResult.data.country}, ${queryResult.data.state})")
                }

                is QueryResult.Error -> {
                    _cityLookupState.value =
                        CityLookupState.Error("Error fetching city: ${queryResult.exception.message}")
                }
            }
        }
    }

    private suspend fun updateForecastWithCityState() {
        // Wait for forecast and city states to complete
        if (_forecastState.value is ForecastState.Success) {
            val forecast = (_forecastState.value as ForecastState.Success).forecast

            // Check city lookup state
            when (val cityLookup = _cityLookupState.value) {
                is CityLookupState.Success -> {
                    val state = cityLookup.city.state
                    val country = cityLookup.city.country

                    // Print state and country when available
                    println("City State: ${state ?: "Not available"}")
                    println("City Country: ${country ?: "Not available"}")

                    // Update forecast.state if state is not null
                    state?.let {
                        forecast.state = it
                        println("Updated Forecast State: ${forecast.state}")

                        // Update forecast state after modifying it
                        _forecastState.value = ForecastState.Success(forecast)
                    }
                }

                else -> {
                    // Handle the case when city lookup is not a success
                    println("CityLookupState is not Success.")
                }
            }
        }
    }

}

