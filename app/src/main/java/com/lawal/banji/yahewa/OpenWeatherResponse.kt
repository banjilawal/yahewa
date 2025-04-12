package com.lawal.banji.yahewa

data class OpenWeatherResponse(
    val locationName: String,
    val main: Main,
    val weather: List<Weather>,
    val current: Current,
    val temperature: Temperature
)



