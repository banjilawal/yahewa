package com.lawal.banji.yahewa.weather.model.free

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val temperatureFeelsLike: Double,
    @SerializedName("min_temp") val lowTemperature: Double,
    @SerializedName("max_temp") val highTemperature: Double,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val percentHumidity: Double
)
