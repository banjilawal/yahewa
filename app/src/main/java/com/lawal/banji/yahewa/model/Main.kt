package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val temperatureFeelsLike: Double,
    @SerializedName("temp_min") val lowTemperature: Double,
    @SerializedName("temp_max") val highTemperature: Double,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val percentHumidity: Double
)
