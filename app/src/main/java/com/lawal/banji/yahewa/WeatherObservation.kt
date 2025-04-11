package com.lawal.banji.yahewa

import android.graphics.drawable.Drawable

data class WeatherObservation(
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val currentTemperature: Double,
    val feelsLikeTemperature: Double,
    val description: String,
    val lowTemperature: Double,
    val highTemperature: Double,
    val percentHumidity: Double,
    val pressure: Int,
    val icon: Drawable?
)

//http://api.openweathermap.org/geo/1.0/reverse?lat={lat}&lon={lon}&limit=1&appid={API key}