package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class City(
//    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = "US",
//    @SerializedName("lon") val longitude: Double,
//    @SerializedName("lat") val latitude: Double,
    @SerializedName("timezone") val timezone: Int? = null,
    @SerializedName("coord") val coordinates: Coordinates,
    var zipCode: String? = null
)