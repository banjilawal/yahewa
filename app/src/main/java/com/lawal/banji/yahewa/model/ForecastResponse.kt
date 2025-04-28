package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("city") val city: City,
    @SerializedName("cnt") val numberOfForecasts: Int,
    @SerializedName("list") val forecastList: List<DailyForecast>
)
