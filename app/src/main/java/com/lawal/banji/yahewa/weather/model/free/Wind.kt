package com.lawal.banji.yahewa.weather.model.free

import com.google.gson.annotations.SerializedName

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val direction: Int,
    @SerializedName("gust") val gust: Double
)
