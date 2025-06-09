package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.Coordinate
import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.GeoCode
import com.lawal.banji.yahewa.repo.RetrofitApiBuilder.api

sealed class QueryResponseState<out T> {
        data class Success<out T>(val data: T) : QueryResponseState<T>()
        data class Error(val exception: Exception) : QueryResponseState<Nothing>()
}

class AppRepository {

        suspend fun requestGeoCodeByCityName(cityName: String): QueryResponseState<List<GeoCode>> {
//                println("Inside requestGeoCodeByCityName with $cityName")
                return try {
//                        println("Inside try block of requestGeoCodeByCityName")
                        val result = api.getGeoCodeByCityName(cityName = cityName)
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestGeoCodeByZipCode(zipCode: String): QueryResponseState<List<GeoCode>> {
                return try {
                        val result = api.getGeoCodeByZipCode(zipCode = zipCode)
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestReverseGeoCode(coordinate: Coordinate): QueryResponseState<List<GeoCode>> {
//                println("Inside requestGeoCodeByCoordinate with $coordinate")
                return try {
                        val result = api.getReverseGeoCode(latitude=coordinate.latitude, longitude=coordinate.longitude)
//                        println("JSON reverse geocode response for coordinate $coordinate is $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestCurrentWeatherByCoordinate(coordinate: Coordinate): QueryResponseState<CurrentWeather> {
//                println("Inside requestCurrentWeatherByCoordinate with $coordinate")
                return try {
                        val result = api.getCurrentWeatherByCoordinate(latitude = coordinate.latitude, longitude = coordinate.longitude)
//                        println("JSON current weather response for coordinate $coordinate is $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }

        suspend fun requestCurrentWeatherByZipCode(zipCode: String): QueryResponseState<CurrentWeather> {
//                println("Inside requestCurrentWeatherByZipCode with  $zipCode")
                return try {
                        val result = api.getCurrentWeatherByZipcode(zipCode = zipCode)
//                        println("JSON current weather response for zip code :$zipCode is $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) {  QueryResponseState.Error(e)  }
        }

        suspend fun requestForecastByCoordinate(coordinate: Coordinate): QueryResponseState<Forecast> {
//                System.out.println("fINSIDE fetchForecastGroup latitude:$latitude longitude:$longitude count:$count")
                return try {
//                        println("finside the try block")
                        val result = api.getForecastByCoordinate(latitude = coordinate.latitude, longitude = coordinate.longitude)
                        // Print the JSON response to the console
//                        println("Forecasts JSON Response: $result")
                        QueryResponseState.Success(result)
                } catch (e: Exception) { QueryResponseState.Error(e)  }
        }
}