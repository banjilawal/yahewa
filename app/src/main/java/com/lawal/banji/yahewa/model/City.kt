package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("name") val name: String,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double
)