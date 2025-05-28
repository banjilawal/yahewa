package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.Coordinate
import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.GeoCode
import com.lawal.banji.yahewa.repo.RetrofitInstance.api

sealed class QueryResponseState<out T> {
        data class Success<out T>(val data: T) : QueryResponseState<T>()
        data class Error(val exception: Exception) : QueryResponseState<Nothing>()
}

class AppRepository {

        suspend fun requestGeoCodeByZipCode(zipCode: String, apiKey: String): QueryResponseState<GeoCode> {
                return try {
                        val result = api.getGeoCodeByZipCode(zipCode = zipCode, apiKey = apiKey)
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestCurrentWeatherByCoordinate(coordinate: Coordinate, apiKey: String): QueryResponseState<CurrentWeather> {
                println("Inside requestCurrentWeatherByCoordinates with $coordinate")
                return try {
                        val result = api.getCurrentWeatherByCoordinate(latitude = coordinate.latitude, longitude = coordinate.longitude, apiKey = apiKey)
                        println("JSON current weather response for coordinate $coordinate is $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestCurrentWeatherByZipCode(zipCode: String, apiKey: String): QueryResponseState<CurrentWeather> {
                println("Inside requestCurrentWeatherByZipCode with  $zipCode")
                return try {
                        val result = api.getCurrentWeatherByZipcode(zipCode = zipCode, apiKey = apiKey)
                        println("JSON current weather response for zip code :$zipCode is $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) {  QueryResponseState.Error(e)  }
        }

        suspend fun requestForecastByCoordinate(coordinate: Coordinate, numberOfRecords: Int, apiKey:String): QueryResponseState<Forecast> {
//                System.out.println("fINSIDE fetchForecastGroup latitude:$latitude longitude:$longitude count:$count")
                return try {
//                        println("finside the try block")
                        val result = api.getForecastByCoordinate(
                                latitude = coordinate.latitude,
                                longitude = coordinate.longitude,
                                count = numberOfRecords,
                                apiKey = apiKey
                        )
                        // Print the JSON response to the console
//                        println("Forecasts JSON Response: $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }
}