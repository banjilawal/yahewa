package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName
import com.lawal.banji.yahewa.model.commercial.Current

data class Forecast(
    val coord: Coordinates,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val sys: Sys,
    val wind: Wind,
    val current: Current,
    val clouds: Clouds,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("dt") val unixTime: Long,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("name") val city: String,
)
