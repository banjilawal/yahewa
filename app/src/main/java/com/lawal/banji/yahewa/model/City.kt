package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class City(
//    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = "US",
    @SerializedName("timezone") val timezone: Int? = null,
    @SerializedName("coord") val coordinates: Coordinates,
    var zipCode: String? = null
)

sealed class CityLookupState {
    object Loading : CityLookupState()
    data class Success(val city: City) : CityLookupState()
    data class Error(val message: String) : CityLookupState()
}