package com.lawal.banji.yahewa.weather

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id") val id : Int,
    @SerializedName("main")  val mainParameters: String,
    @SerializedName("description") val  description: String,
    @SerializedName("icon")  val iconId: String
)
