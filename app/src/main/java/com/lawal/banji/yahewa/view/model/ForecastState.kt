package com.lawal.banji.yahewa.view.model

import com.lawal.banji.yahewa.model.Forecast

sealed class ForecastState {
    object Loading : ForecastState()
    data class Success(val forecast: Forecast) : ForecastState()
    data class Error(val message: String) : ForecastState()
}