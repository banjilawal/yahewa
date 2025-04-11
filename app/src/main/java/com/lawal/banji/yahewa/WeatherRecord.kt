package com.lawal.banji.yahewa

data class WeatherRecord(
    val latitude: Double,
    val longitude: Double,
    val timeZone: String,
    val timeZoneOffset: Int,
    val current: Current,
    val weather: Weather,
    val daily: Daily
)
