package com.lawal.banji.yahewa.query

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("weather")
    fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "imperial",
        @Query("exclude") exclude: String = "hourly, minutely, daily, alerts",
        @Query("appid") apiKey: String,
    ): Call<OpenWeatherResponse>

    @GET("geo/1.0/reverse")
    fun getLocationName(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("limit") limit: Int = 1,
        @Query("appid") apiKey: String
    ): Call<List<ReverseGeocodingResponse>>
}
