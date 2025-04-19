package com.lawal.banji.yahewa.weather.model.free

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all") val all: String,
    @SerializedName("percentage") val percentCloudiness: Int
)