package com.lawal.banji.yahewa

import android.graphics.drawable.Drawable
import android.media.Image

data class WeatherObservation(
    val location: String,
    val currentTemperature: Temperature,
    val feelsLikeTemperature: Temperature,
    val weatherCondition: String,
    val lowTemperature: Temperature,
    val highTemperature: Temperature,
    val percentHumidity: Int,
    val pressure: Int,
    val icon: Drawable?
)
