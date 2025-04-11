package com.lawal.banji.yahewa

import java.time.LocalDateTime

data class Daily(
    val moonrise: LocalDateTime,
    val moonset: LocalDateTime,
    val moonPhase: Double,
    val summary: String,
    val temperature: Temperature
)
