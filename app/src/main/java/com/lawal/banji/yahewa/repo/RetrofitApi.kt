package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.GeoCode
import com.lawal.banji.yahewa.utils.AppDefault
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RetrofitApi {

    @GET("weather")
    suspend fun getCurrentWeatherByCoordinate(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): CurrentWeather

    @GET("weather")
    suspend fun getCurrentWeatherByZipcode(
        @Query("zip") zipCode: String,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): CurrentWeather

    @GET("forecast/daily")
    suspend fun getForecastByCoordinate(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("cnt") count: Int = AppDefault.NUMBER_OF_FORECASTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): Forecast

    @GET()
    suspend fun getGeoCodeByZipCode(
        @Url url: String = "http://api.openweathermap.org/geo/1.0/zip",
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): List<GeoCode>

    @GET()
    suspend fun getGeoCodeByCityName(
        @Url url: String = "https://api.openweathermap.org/geo/1.0/direct",
        @Query("q") cityName: String,
        @Query("limit") limit: Int = AppDefault.NUMBER_OF_GEOCODE_RESULTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): List<GeoCode>

    @GET()
    suspend fun getReverseGeoCode(
        @Url url: String = "https://api.openweathermap.org/geo/1.0/reverse",
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("limit") limit: Int = AppDefault.NUMBER_OF_GEOCODE_RESULTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): List<GeoCode>
}



