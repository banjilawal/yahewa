package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class FeelsLike(
    @SerializedName("day") val day: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("morn") val morn: Double
)
