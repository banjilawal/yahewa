package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.City
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.ForecastResponse
import com.lawal.banji.yahewa.utils.AppDefault
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("weather")
    suspend fun getForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): Forecast

    @GET("weather")
    suspend fun getForecastByZipcode(
        @Query("zip") zipcode: String,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): Forecast

    @GET("geo/1.0/reverse")
    suspend fun getReverseGeoEncoding(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("limit") limit: Int = 1,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): City

    @GET("forecast/daily")
    suspend fun getForecasts(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("cnt") count: Int = AppDefault.NUMBER_OF_FORECASTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): ForecastResponse
}



