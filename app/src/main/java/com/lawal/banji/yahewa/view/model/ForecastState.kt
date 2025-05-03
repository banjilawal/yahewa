package com.lawal.banji.yahewa.view.model

import com.lawal.banji.yahewa.model.Forecast
import kotlinx.coroutines.flow.MutableStateFlow

sealed class ForecastState {
    object Loading : ForecastState()
    data class Success(val forecast: Forecast) : ForecastState()
    data class Error(val message: String) : ForecastState()
}

private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
val forecastState: MutableStateFlow<ForecastState> get() = _forecastState