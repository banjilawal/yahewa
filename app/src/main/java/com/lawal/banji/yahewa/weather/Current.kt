package com.lawal.banji.yahewa.weather

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Current(
    @SerializedName("dt") val currentTime: LocalDateTime,
    @SerializedName("sunrise") val sunriseTime : LocalDateTime,
    @SerializedName("sunset") val sunsetTime : LocalDateTime,
    @SerializedName("temp") val  temperature : Double,
    @SerializedName("feels_like") val temperatureFeelsLike : Double,
    @SerializedName("pressure") val  pressure : Double,
    @SerializedName("humidity") val  percentHumidity : Int,
   @SerializedName("dew_point")  val dewPoint: Double,
    @SerializedName("uvi")  val uvIndex: Double,
    @SerializedName("clouds") val  percentCloudiness : Double,
    @SerializedName("visibility") val  visibility: Double,
    @SerializedName("wind_speed") val  windSpeed : Double,
    @SerializedName("wind_deg") val  windOrientation: Double,
    @SerializedName("wind_gust") val  windGust: Double,
    @SerializedName("weather") val weather: List<Weather>,
)
