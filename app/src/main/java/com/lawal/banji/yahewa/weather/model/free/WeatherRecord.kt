package com.lawal.banji.yahewa.weather.model.free

import com.google.gson.annotations.SerializedName

data class WeatherRecord(
    val coord: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val sys: Sys,
    val wind: Wind,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("dt") val unixTime: Long,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("name") val name: String,
)

