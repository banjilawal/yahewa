package com.lawal.banji.yahewa.model

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id") val id : Int,
    @SerializedName("main")  val phenomena: String,
    @SerializedName("description") val  description: String,
    @SerializedName("icon")  val iconId: String
)