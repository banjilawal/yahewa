package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("coord") val coordinates: Coordinates,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val unixTime: Long,
    @SerializedName("sys") val  sys: Sys,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("name") val city: String,
    var state: String? = null
)
