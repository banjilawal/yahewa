package com.lawal.banji.yahewa.weather.model

data class WeatherRecord(
    val latitude: Double,
    val longitude: Double,
    val timeZone: String,
    val timeZoneOffset: Int,
    val current: Current,
)
