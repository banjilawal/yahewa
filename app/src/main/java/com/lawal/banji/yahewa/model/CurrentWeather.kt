package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all") val all: String,
    @SerializedName("percentage") val percentCloudiness: Int
)

data class Sys(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val direction: Int,
    @SerializedName("gust") val gust: Double
)

data class Main(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val temperatureFeelsLike: Double,
    @SerializedName("temp_min") val lowTemperature: Double,
    @SerializedName("temp_max") val highTemperature: Double,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val percentHumidity: Double
)

data class CurrentWeather(
    @SerializedName("coord") val coordinates: Coordinates,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("main") val main: Main,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val unixTime: Long,
    @SerializedName("sys") val  sys: Sys,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("name") val city: String,
    var state: String? = null
)

sealed class CurrentWeatherState {
    object Loading : CurrentWeatherState()
    data class Success(val currentWeather: CurrentWeather) : CurrentWeatherState()
    data class Error(val message: String) : CurrentWeatherState()
}