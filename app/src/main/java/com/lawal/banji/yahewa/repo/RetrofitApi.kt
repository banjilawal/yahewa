package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.Forecast
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("weather")
    suspend fun getForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "imperial",
//        @Query("exclude") exclude: String = "hourly, minutely, daily, alerts",
        @Query("appid") apiKey: String
    ): Forecast

//    @GET("geo/1.0/reverse")
//    suspend fun getLocationName(
//        @Query("lat") latitude: Double,
//        @Query("lon") longitude: Double,
//        @Query("limit") limit: Int = 1,
//        @Query("appid") apiKey: String
//    ): Call<List<ReverseGeocodingResponse>>
}
