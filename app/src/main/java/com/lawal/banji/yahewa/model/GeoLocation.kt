package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName
import com.lawal.banji.yahewa.utils.AppDefault

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

data class ZipCodeMetadata(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("zip") val zipCode: String,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String
)

sealed class ZipCodeMetadataState {
    object Loading : ZipCodeMetadataState() // Represents a loading state while forecastRecords are being fetched
    data class Success(val zipCodeMetadata: ZipCodeMetadata) : ZipCodeMetadataState()
    data class Error(val message: String) : ZipCodeMetadataState() // Represents an error state with a message
}

data class Coordinates(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double
)

data class GeoLocation(
//    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String,
    @SerializedName("state") val state: String? = null,
    @SerializedName("country") val country: String? = AppDefault.COUNTRY,
    @SerializedName("timezone") val timezone: Int? = null,
    @SerializedName("zip") val zipCode: String,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double,
) {
    // Computed property to return coordinates
    val coordinates: Pair<Double, Double>
        get() = latitude to longitude
}


sealed class GeoLocationState {
    object Loading : GeoLocationState() // Represents a loading state while forecastRecords are being fetched
    data class Success(val geoLocation: GeoLocation) : GeoLocationState()
    data class Error(val message: String) : GeoLocationState() // Represents an error state with a message
}
