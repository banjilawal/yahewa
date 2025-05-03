package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all") val all: String,
    @SerializedName("percentage") val percentCloudiness: Int
)