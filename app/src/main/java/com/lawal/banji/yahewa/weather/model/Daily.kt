package com.lawal.banji.yahewa.weather.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Daily(
    @SerializedName("") val  moonrise: LocalDateTime,
    @SerializedName("") val moonset: LocalDateTime,
    @SerializedName("") val moonPhase: Double,
    @SerializedName("") val summary: String,
   @SerializedName("") val  temperature: Temperature
)
