package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("name") val name: String,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("timezone") val timezone: Int? = null,
    @SerializedName("coord") val coordinate: Coordinate,
    var zipCode: String? = null
)
sealed class CityState {
    object Loading : CityState()
    data class Success(val city: City) : CityState()
    data class Error(val message: String) : CityState()
}

data class Coordinate(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double
)
sealed class CoordinateState {
    object Loading : CoordinateState()
    data class Success(val coordinate: Coordinate) : CoordinateState()
    data class Error(val message: String) : CoordinateState()
}

data class GeoCode(
    @SerializedName("name") val name: String,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("country") val country: String? = null,
    @SerializedName("zip") val zipCode: String? = null,
    @SerializedName("state") var state: String? = null,
) {
    val coordinate: Coordinate
        get() = Coordinate(longitude = longitude, latitude = latitude)

    fun getTitle(): String {
        var title = "$name, $country"
        if (state != null && state!!.isNotEmpty() && country!!.isNotEmpty() &&  country?.uppercase()   == "US") {
            title = "$name, $state"
        }
        return title
    }
}
sealed class GeoCodeState {
    object Loading : GeoCodeState()
    data class Success(val geoCode: GeoCode) : GeoCodeState()
    data class Error(val message: String) : GeoCodeState() // Represents an error state with a message
}
