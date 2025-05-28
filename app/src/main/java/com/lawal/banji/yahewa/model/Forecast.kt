package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("day") val day: Double,
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("morn") val morn: Double
)

data class FeelsLike(
    @SerializedName("day") val day: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("morn") val morn: Double
)

data class ForecastRecord (
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long,
    @SerializedName("temp") val temperature: Temperature,
    @SerializedName("feels_like") val feelsLike: FeelsLike,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val humidity: Double,
    @SerializedName("weather") val weatherItems: List<WeatherItem>,
    @SerializedName("speed") val windSpeed: Double,
    @SerializedName("deg") val windOrientation: Double,
    @SerializedName("gust") val windGusts: Double,
    @SerializedName("clouds") val percentCloudiness: Double,
    @SerializedName("pop") val precipitationProbability: Double,
    @SerializedName("rain") val rainVolume: Double
)

data class Forecast(
    @SerializedName("city") val city: City,
    @SerializedName("cnt") val numberOfForecasts: Int,
    @SerializedName("list") val forecastRecords: List<ForecastRecord>
)
sealed class ForecastState {
    object Loading : ForecastState() // Represents a loading state while forecastRecords are being fetched
    data class Success(val forecast: Forecast) : ForecastState()
    data class Error(val message: String) : ForecastState() // Represents an error state with a message
}
