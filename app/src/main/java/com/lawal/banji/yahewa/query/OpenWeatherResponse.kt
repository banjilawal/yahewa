package com.lawal.banji.yahewa.query

import com.lawal.banji.yahewa.weather.Temperature
import com.lawal.banji.yahewa.weather.Current
import com.lawal.banji.yahewa.weather.Main
import com.lawal.banji.yahewa.weather.Weather

data class OpenWeatherResponse(
    val locationName: String,
    val main: Main,
    val weather: List<Weather>,
    val current: Current,
    val temperature: Temperature
)



