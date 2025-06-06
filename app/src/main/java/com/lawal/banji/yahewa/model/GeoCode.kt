package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("name") val name: String,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("timezone") val timezone: Int? = null,
    @SerializedName("coord") val coordinate: Coordinate,
    var zipCode: String? = null
) {
    override fun toString(): String {
        return "name:name, $state  $zipCode country:$country" +
                "\nlongitude:${coordinate.longitude}\nlatitude$coordinate.latitude "
    }
}
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
    @SerializedName("name") var name: String,
    @SerializedName("lon") var longitude: Double,
    @SerializedName("lat") var latitude: Double,
    @SerializedName("country") var country: String? = null,
    @SerializedName("zip") var zipCode: String? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("timezone") var timezone: Int? = null
) {
    val coordinate: Coordinate
        get() = Coordinate(longitude = longitude, latitude = latitude)

    fun getTitle(): String {
        return "$name, $state $zipCode $country"
    }
    override fun toString(): String {
        return  "$name, $state $zipCode. $country"
    }
}
sealed class GeoCodeState {
    object Loading : GeoCodeState()
    data class Success(val geoCode: GeoCode) : GeoCodeState()
    data class Error(val message: String) : GeoCodeState() // Represents an error state with a message
}
