package com.lawal.banji.yahewa.model

data class City(
    val name: String,
    val state: String? = null,
    val country: String?= null,
    val latitude: Double,
    val longitude: Double
)
