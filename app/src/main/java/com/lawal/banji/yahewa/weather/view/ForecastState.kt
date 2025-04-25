package com.lawal.banji.yahewa.weather.view

import com.lawal.banji.yahewa.weather.model.Forecast
import kotlinx.coroutines.flow.MutableStateFlow

sealed class WeatherRecordState {
    object Loading : WeatherRecordState()
    data class Success(val forecast: Forecast) : WeatherRecordState()
    data class Error(val message: String) : WeatherRecordState()
}

private val _weatherRecordState = MutableStateFlow<WeatherRecordState>(WeatherRecordState.Loading)
val weatherRecordState: MutableStateFlow<WeatherRecordState> get() = _weatherRecordState