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

data class Coordinate(
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double
)

sealed class CoordinateState {
    object Loading : CoordinateState() // Represents a loading state while forecastRecords are being fetched
    data class Success(val coordinate: Coordinate) : CoordinateState()
    data class Error(val message: String) : CoordinateState() // Represents an error state with a message
}

data class GeoCode(
    @SerializedName("name") val name: String,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("country") val country: String? = null,
    @SerializedName("zip") val zipCode: String? = null,
    @SerializedName("state") val state: String? = null,
) {
    val coordinate: Coordinate
        get() = Coordinate(longitude = longitude, latitude = latitude)
}
sealed class GeoCodeState {
    object Loading : GeoCodeState() // Represents a loading state while forecastRecords are being fetched
    data class Success(val geoCode: GeoCode) : GeoCodeState()
    data class Error(val message: String) : GeoCodeState() // Represents an error state with a message
}

data class ReverseGeoCoding(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String?,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("state") val state: String? = null,
)

sealed class ReverseGeoCodingState {
    object Loading : ReverseGeoCodingState() // Represents a loading state while forecastRecords are being fetched
    data class Success(val reverseGeoCoding: ReverseGeoCoding) : ReverseGeoCodingState()
    data class Error(val message: String) : ReverseGeoCodingState() // Represents an error state with a message
}
