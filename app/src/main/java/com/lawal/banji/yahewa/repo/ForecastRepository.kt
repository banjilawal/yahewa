package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.City
import com.lawal.banji.yahewa.model.Coordinates
import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.model.ForecastGroup
import com.lawal.banji.yahewa.repo.RetrofitInstance.api

sealed class QueryResponseState<out T> {
        data class Success<out T>(val data: T) : QueryResponseState<T>()
        data class Error(val exception: Exception) : QueryResponseState<Nothing>()
}

class ForecastRepository {

        suspend fun fetchByCoordinates(latitude: Double, longitude: Double, apiKey: String): QueryResponseState<CurrentWeather> {
                return try {
                        val result = api.getForecastByCoordinates(latitude = latitude, longitude = longitude, apiKey = apiKey)
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun fetchCurrentWeatherByCoordinates(coordinates: Coordinates,  apiKey: String): QueryResponseState<CurrentWeather> {
                return try {
                        val result = api.getCurrentWeatherByCoordinates(latitude = coordinates.latitude, longitude = coordinates.longitude, apiKey = apiKey)
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun fetchCurrentWeatherByZipcode(zipCode: String, apiKey: String): QueryResponseState<CurrentWeather> {
                return try {
                        val result = api.getForecastByZipcode(zipCode = zipCode, apiKey = apiKey)
                        QueryResponseState.Success(result)
                } catch (e: Exception) {  QueryResponseState.Error(e)  }
        }

        suspend fun fetchByZipcode(zipCode: String, apiKey: String): QueryResponseState<CurrentWeather> {
                return try {
                        val result = api.getForecastByZipcode(zipCode = zipCode, apiKey = apiKey)
                        QueryResponseState.Success(result)
                } catch (e: Exception) {  QueryResponseState.Error(e)  }
        }

        suspend fun fetchForecastGroupByCoordinates(
                coordinates: Coordinates, forecastCount: Int, apiKey:String
        ): QueryResponseState<ForecastGroup> {
//                System.out.println("fINSIDE fetchForecastGroup latitude:$latitude longitude:$longitude count:$count")
                return try {
//                        println("finside the try block")
                        val result = api.getForecasts(
                                latitude = coordinates.latitude,
                                longitude = coordinates.longitude,
                                count = forecastCount ,
                                apiKey = apiKey
                        )
                        // Print the JSON response to the console
//                        println("Forecasts JSON Response: $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun fetchForecastGroup(
               latitude: Double,
               longitude: Double,
               count: Int,
               apiKey:String
        ): QueryResponseState<ForecastGroup> {
//                System.out.println("fINSIDE fetchForecastGroup latitude:$latitude longitude:$longitude count:$count")
                return try {
//                        println("finside the try block")
                        val result = api.getForecasts(latitude = latitude, longitude = longitude, count = count , apiKey = apiKey)
                        // Print the JSON response to the console
//                        println("Forecasts JSON Response: $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun fetchReverseGeoCoding(latitude: Double, longitude: Double, apiKey: String): QueryResponseState<City> {
                return try {
                        val result = api.getReverseGeoEncoding(latitude = latitude, longitude = longitude, apiKey = apiKey)
                        // Print the JSON response to the console
                        println("Reverse Geocoding JSON Response: $result")

                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e) }
        }
}