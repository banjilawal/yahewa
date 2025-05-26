package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.GeoCoding
import com.lawal.banji.yahewa.model.ReverseGeoCoding
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
    suspend fun getForecastByZipcode(
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
    suspend fun getGeoCodingByZipCode(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): GeoCoding

    @GET("geo/1.0/direct")
    suspend fun getGeoCodingByCityName(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): GeoCoding

    @GET("geo/1.0/reverse")
    suspend fun getReverseGeoCodingByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("limit") limit: Int = AppDefault.NUMBER_OF_REVERSE_GEO_ENCODING_RESULTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): ReverseGeoCoding

    @GET("geo/1.0/reverse")
    suspend fun getReverseGeoEncoding(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("limit") limit: Int = AppDefault.NUMBER_OF_REVERSE_GEO_ENCODING_RESULTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): ReverseGeoCoding

    @GET("forecast/daily")
    suspend fun getForecasts(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = AppDefault.DEFAULT_MEASUREMENT_SYSTEM,
        @Query("cnt") count: Int = AppDefault.NUMBER_OF_FORECASTS,
        @Query("appid") apiKey: String = AppDefault.API_KEY
    ): Forecast
}



