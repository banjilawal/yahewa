package com.lawal.banji.yahewa.query

import com.lawal.banji.yahewa.model.ForecastResponse

sealed class ForecastResponseState {
    object Loading : ForecastResponseState() // Represents a loading state while forecasts are being fetched
    data class Success(val forecastResponse: ForecastResponse) : ForecastResponseState()
    data class Error(val message: String) : ForecastResponseState() // Represents an error state with a message
}
