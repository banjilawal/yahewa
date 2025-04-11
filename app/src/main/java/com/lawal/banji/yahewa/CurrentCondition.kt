package com.lawal.banji.yahewa

data class CurrentCondition(
    val temperature: Temperature,
    val weather: Weather,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Float,
    val windDirection: String,
    val visibility: Int,
    val sunrise: Long,
    val sunset: Long
)
