package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.City
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.PredictionGroup
import com.lawal.banji.yahewa.query.QueryResult
import com.lawal.banji.yahewa.repo.RetrofitInstance.api

class ForecastRepository {

        suspend fun fetchByCoordinates(latitude: Double, longitude: Double, apiKey: String): QueryResult<Forecast> {
                return try {
                        val result = api.getForecastByCoordinates(latitude = latitude, longitude = longitude, apiKey = apiKey)
                        QueryResult.Success(result)
                } catch (e: Exception) { QueryResult.Error(e)  }
        }

        suspend fun fetchByZipcode(zipcode: String, apiKey: String): QueryResult<Forecast> {
                return try {
                        val result = api.getForecastByZipcode(zipcode = zipcode, apiKey = apiKey)
                        QueryResult.Success(result)
                } catch (e: Exception) {  QueryResult.Error(e)  }
        }

        suspend fun fetchForecasts(latitude: Double, longitude: Double, count: Int, apiKey:String): QueryResult<PredictionGroup> {
                return try {
                        val result = api.getForecasts(latitude = latitude, longitude = longitude, count = count , apiKey = apiKey)
                        QueryResult.Success(result)
                } catch (e: Exception) { QueryResult.Error(e)  }
        }

        suspend fun fetchReverseGeoCoding(latitude: Double, longitude: Double, apiKey: String): QueryResult<City> {
                return try {
                        val result = api.getReverseGeoEncoding(latitude = latitude, longitude = longitude, apiKey = apiKey)
                        // Print the JSON response to the console
                        println("Reverse Geocoding JSON Response: $result")

                        QueryResult.Success(result)
                } catch (e: Exception) { QueryResult.Error(e) }
        }
}