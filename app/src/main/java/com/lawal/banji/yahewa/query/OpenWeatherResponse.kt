package com.lawal.banji.yahewa.query

import com.lawal.banji.yahewa.weather.model.Temperature
import com.lawal.banji.yahewa.weather.model.Current
import com.lawal.banji.yahewa.weather.model.free.Main
import com.lawal.banji.yahewa.weather.model.free.Weather

data class OpenWeatherResponse(
    val locationName: String,
    val main: Main,
    val weather: List<Weather>,
    val current: Current,
    val temperature: Temperature
)



