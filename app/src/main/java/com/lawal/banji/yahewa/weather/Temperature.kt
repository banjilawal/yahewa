package com.lawal.banji.yahewa.weather

data class Temperature(
    val dayTimeAverage: Double,
    val dayTimeLow: Double,
    val dayTimeHigh: Double,
    val nightTimeAverage: Double,
    val eveningAverage: Double,
    val morningAverage: Double
)