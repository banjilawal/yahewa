package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.Coordinate
import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.GeoCode
import com.lawal.banji.yahewa.model.ReverseGeoCoding
import com.lawal.banji.yahewa.model.ZipCodeMetadata
import com.lawal.banji.yahewa.repo.RetrofitInstance.api

sealed class QueryResponseState<out T> {
        data class Success<out T>(val data: T) : QueryResponseState<T>()
        data class Error(val exception: Exception) : QueryResponseState<Nothing>()
}

class AppRepository {

        suspend fun fetchByCoordinates(latitude: Double, longitude: Double, apiKey: String): QueryResponseState<CurrentWeather> {
                return try {
                        val result = api.getForecastByCoordinates(latitude = latitude, longitude = longitude, apiKey = apiKey)
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestGeoCodingByZipCode(zipCode: String, apiKey: String): QueryResponseState<GeoCode> {
                return try {
                        val result = api.getGeoCodeByZipCode(zipCode = zipCode, apiKey = apiKey)
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestGeoCodeByZipCode(zipCode: String, apiKey: String): QueryResponseState<GeoCode> {
                return try {
                        val result = api.getGeoCodeByZipCode(zipCode = zipCode, apiKey = apiKey)
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestGeoCodeByCoordinate(coordinate: Coordinate, apiKey: String): QueryResponseState<GeoCode> {
                return try {
                        val result = api.getGeoCodeByCoordinate(latitude = coordinate.latitude, longitude = coordinate.longitude, apiKey = apiKey)
                        // Print the JSON response to the console
                        println("Reverse Geocoding JSON Response: $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e) }
        }

        suspend fun requestCurrentWeatherByCoordinate(coordinate: Coordinate, apiKey: String): QueryResponseState<CurrentWeather> {
                System.out.println("Inside requestCurrentWeatherByCoordinates with $coordinate")
                return try {
                        val result = api.getCurrentWeatherByCoordinate(latitude = coordinate.latitude, longitude = coordinate.longitude, apiKey = apiKey)
                        System.out.println("JSON current weather response for coordinate $coordinate is $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestCurrentWeatherByZipCode(zipCode: String, apiKey: String): QueryResponseState<CurrentWeather> {
                System.out.println("Inside requestCurrentWeatherByZipCode with  $zipCode")
                return try {
                        val result = api.getForecastByZipcode(zipCode = zipCode, apiKey = apiKey)
                        System.out.println("JSON current weather response for zip code :$zipCode is $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) {  QueryResponseState.Error(e)  }
        }

        suspend fun requestForecastByCoordinates(coordinate: Coordinate, numberOfRecords: Int, apiKey:String): QueryResponseState<Forecast> {
//                System.out.println("fINSIDE fetchForecastGroup latitude:$latitude longitude:$longitude count:$count")
                return try {
//                        println("finside the try block")
                        val result = api.getForecasts(
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

        suspend fun requestZipCoddMetadata(zipCode: String, apiKey:String): QueryResponseState<ZipCodeMetadata>{
//                System.out.println("fINSIDE fetchForecastGroup latitude:$latitude longitude:$longitude count:$count")
                return try {
//                        println("finside the try block")
                        val result = api.getZipCodeMetadata(zipCode = zipCode, apiKey = apiKey)
                        // Print the JSON response to the console
//                        println("Forecasts JSON Response: $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun fetchByZipcode(zipCode: String, apiKey: String): QueryResponseState<CurrentWeather> {
                return try {
                        val result = api.getForecastByZipcode(zipCode = zipCode, apiKey = apiKey)
                        QueryResponseState.Success(result)
                } catch (e: Exception) {  QueryResponseState.Error(e)  }
        }

        suspend fun fetchForecastGroupByCoordinates(
                coordinate: Coordinate, forecastCount: Int, apiKey:String
        ): QueryResponseState<Forecast> {
//                System.out.println("fINSIDE fetchForecastGroup latitude:$latitude longitude:$longitude count:$count")
                return try {
//                        println("finside the try block")
                        val result = api.getForecasts(
                                latitude = coordinate.latitude,
                                longitude = coordinate.longitude,
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
        ): QueryResponseState<Forecast> {
//                System.out.println("fINSIDE fetchForecastGroup latitude:$latitude longitude:$longitude count:$count")
                return try {
//                        println("inside the try block")
                        val result = api.getForecasts(latitude = latitude, longitude = longitude, count = count , apiKey = apiKey)
                        // Print the JSON response to the console
//                        println("Forecasts JSON Response: $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun fetchReverseGeoCoding(latitude: Double, longitude: Double, apiKey: String): QueryResponseState<ReverseGeoCoding> {
                return try {
                        val result = api.getReverseGeoEncoding(latitude = latitude, longitude = longitude, apiKey = apiKey)
                        // Print the JSON response to the console
                        println("Reverse Geocoding JSON Response: $result")

                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e) }
        }
}