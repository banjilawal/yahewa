package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all") val all: String,
    @SerializedName("percentage") val percentCloudiness: Int
) {
    override fun toString(): String {
        return "$percentCloudiness% clouds"
    }
}

data class Sys(
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
) {
    override fun toString(): String {
        return "Sunrise: ${unixTimeToString(sunrise)}, Sunset: ${unixTimeToString(sunset)}"
    }
    private fun unixTimeToString(unixTime: Long): String {
        return String.format("%tR", unixTime * 1000)
    }
}

data class Wind(
    @SerializedName("speed") val speed: Double,
    @SerializedName("deg") val direction: Int,
    @SerializedName("gust") val gust: Double
) {
    override fun toString(): String {
        return "Wind[speed: $speed, direction: $direction]"
    }
}

data class Main(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val temperatureFeelsLike: Double,
    @SerializedName("temp_min") val lowTemperature: Double,
    @SerializedName("temp_max") val highTemperature: Double,
    @SerializedName("pressure") val pressure: Double,
    @SerializedName("humidity") val percentHumidity: Double
) {
    override fun toString(): String {
        return "temperature: $temperature, pressure: $pressure, humidity: $percentHumidity"
    }
}

data class WeatherItem(
    @SerializedName("id") val id : Int,
    @SerializedName("main")  val phenomena: String,
    @SerializedName("description") val  description: String,
    @SerializedName("icon")  val iconId: String
)   {
    override fun toString():String {
        return "$description"
    }
}

data class CurrentWeather(
    @SerializedName("coord") val coordinate: Coordinate,
    @SerializedName("weather") val weatherItems: List<WeatherItem>,
    @SerializedName("main") val main: Main,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val unixTime: Long,
    @SerializedName("sys") val  sys: Sys,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("name") val cityName: String,
    var state: String? = null
) {
    override fun toString(): String {
        return "\nCurrent Weather:$main visibility:$visibility wind:$wind. $weatherItems"
    }
}
sealed class CurrentWeatherState {
    object Loading : CurrentWeatherState()
    data class Success(val currentWeather: CurrentWeather) : CurrentWeatherState()
    data class Error(val message: String) : CurrentWeatherState()
}