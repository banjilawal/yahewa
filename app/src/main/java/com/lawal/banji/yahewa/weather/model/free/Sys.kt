package com.lawal.banji.yahewa.weather.model.free

import com.google.gson.annotations.SerializedName

data class Sys(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)