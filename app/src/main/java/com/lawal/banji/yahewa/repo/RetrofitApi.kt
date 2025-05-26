package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.City
import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.GeoLocation
import com.lawal.banji.yahewa.model.ZipCodeMetadata
import com.lawal.banji.yahewa.utils.AppDefault
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("weather")
    suspend fun getCurrentWeatherByCoordinates(
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

    @GET("weather")
    suspend fun getForecastByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): CurrentWeather

    @GET("geo/1.0/zip")
    suspend fun getZipCodeMetadata(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): ZipCodeMetadata

    @GET("geo/1.0/zip")
    suspend fun getGeoLocationByZipCode(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): GeoLocation

    @GET("geo/1.0/reverse")
    suspend fun getGeoLocationByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("limit") limit: Int = AppDefault.NUMBER_OF_REVERSE_GEO_ENCODING_RESULTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): GeoLocation


    @GET("weather")
    suspend fun getForecastByZipcode(
        @Query("zip") zipCode: String,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): CurrentWeather

    @GET("geo/1.0/reverse")
    suspend fun getReverseGeoEncoding(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("limit") limit: Int = AppDefault.NUMBER_OF_REVERSE_GEO_ENCODING_RESULTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): City

    @GET("forecast/daily")
    suspend fun getForecasts(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("cnt") count: Int = AppDefault.NUMBER_OF_FORECASTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): Forecast
}



