package com.lawal.banji.yahewa.weather.view

import com.lawal.banji.yahewa.weather.model.WeatherRecord
import kotlinx.coroutines.flow.MutableStateFlow

sealed class RecordState {
    object Loading : RecordState()
    data class Success(val weatherRecord: WeatherRecord) : RecordState()
    data class Error(val message: String) : RecordState()
}

private val _recordState = MutableStateFlow<RecordState>(RecordState.Loading)
val recordState: MutableStateFlow<RecordState> get() = _recordState