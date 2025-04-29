package com.lawal.banji.yahewa.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.model.DailyForecast
import com.lawal.banji.yahewa.repo.ForecastRepository
import com.lawal.banji.yahewa.repo.QueryResult
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.getRandomCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForecastViewModel(private val repository: ForecastRepository) : ViewModel() {

    private val _forecastList = MutableStateFlow<List<DailyForecast>>(emptyList())
    val forecastList: StateFlow<List<DailyForecast>> = _forecastList


    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> get() = _forecastState

    private var _zipcode: MutableStateFlow<String?> = MutableStateFlow(null)
    val zipcode: StateFlow<String?> get() = _zipcode

    private val _cityLookupState = MutableStateFlow<CityLookupState>(CityLookupState.Loading)
    val cityLookupState: StateFlow<CityLookupState> get() = _cityLookupState

    init {
        viewModelScope.launch {
            val location = getRandomCity()
            println("Location: ${location.name} (${location.coordinates.latitude}, ${location.coordinates.longitude})")

            // Fetch the forecast
            fetchForecastByCoordinates(
                latitude = location.coordinates.latitude,
                longitude = location.coordinates.longitude,
                apiKey = AppDefault.API_KEY
            )
        }
    }

    // Public method to set the zip code so the ViewModel can handle fetching
    fun setZipcode(newZipcode: String) {
        if (newZipcode != _zipcode.value) {
            _zipcode.value = newZipcode
            fetchForecastByZipcode(newZipcode, AppDefault.API_KEY)
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

    private fun fetchForecastByZipcode(zipcode: String, apiKey: String) {
        viewModelScope.launch {
            when (val queryResult = repository.fetchByZipcode(zipcode, apiKey)) {
                is QueryResult.Success -> _forecastState.value = ForecastState.Success(queryResult.data)
                is QueryResult.Error -> _forecastState.value = ForecastState.Error(queryResult.exception.message ?: "Unknown Error")
            }
        }
    }
}

