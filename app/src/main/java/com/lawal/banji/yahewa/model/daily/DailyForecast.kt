package com.lawal.banji.yahewa.model.daily

import com.google.gson.annotations.SerializedName
import com.lawal.banji.yahewa.model.Temperature
import com.lawal.banji.yahewa.model.Weather

data class Data (
    @SerializedName("sunrise") val sunrise: Double,
    @SerializedName("sunset") val sunset: Double,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val humidity: Double,
    @SerializedName("speed") val windSpeed: Double,
    @SerializedName("deg") val windDegree: Double,
    @SerializedName("gust") val windGusts: Double,
    @SerializedName("clouds") val percentCloudiness: Double,
    @SerializedName("temp") val temperatures: Temperature,
    @SerializedName("feels_like") val feelsLike: FeelsLike,
    @SerializedName("weather") val weather: List<Weather>,
)