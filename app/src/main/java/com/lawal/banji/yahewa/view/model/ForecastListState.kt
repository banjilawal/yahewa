package com.lawal.banji.yahewa.view.model

import com.lawal.banji.yahewa.model.DailyForecast

sealed class ForecastListState {
    object Loading : ForecastListState() // Represents a loading state while forecasts are being fetched
    data class Success(val forecastList: List<DailyForecast>) : ForecastListState() // Holds the list of forecasts on a successful fetch
    data class Error(val message: String) : ForecastListState() // Represents an error state with a message
}
