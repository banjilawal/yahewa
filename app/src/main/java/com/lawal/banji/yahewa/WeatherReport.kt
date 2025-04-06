package com.lawal.banji.yahewa

import android.health.connect.datatypes.units.Temperature

data class WeatherReport(
    val location: String,
    val currentTemperature: Int,
    val feelsLikeTemperature: Int,
    val weatherCondition: String,
    val lowTemperature: Int,
    val highTemperature: Int,
    val percentHumidity: Int,
    val pressure: Int
)
