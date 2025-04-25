package com.lawal.banji.yahewa.weather.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.repo.Result
import com.lawal.banji.yahewa.repo.WeatherRepository
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.weather.model.WeatherRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {

    private val _zipcode = MutableStateFlow<String>("")
    val zipcode: StateFlow<String> get() = _zipcode

    private val _weatherRecord = MutableStateFlow<WeatherRecord?>(null)
    val weatherRecord: StateFlow<WeatherRecord?> get() = _weatherRecord

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
            is Result.Success -> _weatherRecord.value = result.data
            is Result.Error -> _errorMessage.value = "Error: ${result.exception.message}"
        }
    }

    fun fetchWeatherData(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            when (val result = repository.fetchRecordByCoordinate(latitude, longitude, apiKey)) {
                is Result.Success -> _weatherRecord.value = result.data
                is Result.Error -> _errorMessage.value = "Error: ${result.exception.message}"
            }
        }
    }
}
