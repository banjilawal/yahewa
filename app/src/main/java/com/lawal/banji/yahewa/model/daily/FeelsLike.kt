package com.lawal.banji.yahewa.model.daily

import com.google.gson.annotations.SerializedName

data class FeelsLike(
    @SerializedName("temp") val current: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val min: Double,
    @SerializedName("temp_max") val max: Double
)
