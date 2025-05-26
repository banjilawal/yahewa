package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

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
