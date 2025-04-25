package com.lawal.banji.yahewa.weather.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.repo.ForecastRepository
import com.lawal.banji.yahewa.repo.QueryResult
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.weather.model.Forecast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForecastViewModel(private val repository: ForecastRepository) : ViewModel() {

    private val _zipcode = MutableStateFlow<String>("")
    val zipcode: StateFlow<String> get() = _zipcode

    private val _forecast = MutableStateFlow<Forecast?>(null)
    val forecast: StateFlow<Forecast?> get() = _forecast

    private val _errorMessage =MutableStateFlow<String>("")
    val errorMessage:  StateFlow<String> get() = _errorMessage

    init {
        viewModelScope.launch {
            fetchForDefaultLocation()
        }
    }

    private suspend fun fetchForDefaultLocation() {
        when (val result = repository.fetchRecordByCoordinate(
            latitude = AppDefault.LATITUDE,
            longitude = AppDefault.LONGITUDE,
            apiKey = AppDefault.API_KEY
        )) {
            is QueryResult.Success -> _forecast.value = result.data
            is QueryResult.Error -> _errorMessage.value = "Error: ${result.exception.message}"
        }
    }

    fun fetchWeatherData(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            when (val result = repository.fetchRecordByCoordinate(latitude, longitude, apiKey)) {
                is QueryResult.Success -> _forecast.value = result.data
                is QueryResult.Error -> _errorMessage.value = "Error: ${result.exception.message}"
            }
        }
    }
}
