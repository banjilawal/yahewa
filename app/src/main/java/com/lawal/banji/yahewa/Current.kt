package com.lawal.banji.yahewa

import java.time.LocalDateTime

data class Current(
    val currentTime: LocalDateTime,
    val sunriseTime : LocalDateTime,
    val sunsetTime : LocalDateTime,
    val temperature : Double,
    val temperatureFeelsLike : Double,
    val pressure : Double,
    val percentHumidity : Int,
    val dewPoint: Double,
    val uvIndex: Double,
    val visibility: Double,
    val percentCloudiness : Double,
    val windSpeed : Double,
    val windOrientation: Double,
    val rainVolume: Double,
    val snowVolume: Double
)
