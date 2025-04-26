package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.repo.RetrofitInstance.api

class ForecastRepository {

        suspend fun fetchByCoordinates(
                latitude: Double,
                longitude: Double,
                apiKey: String
        ): QueryResult<Forecast> {
                return try {
                        val result = api.getForecastByCoordinates(latitude = latitude, longitude = longitude, apiKey = apiKey)
                        QueryResult.Success(result)
                } catch (e: Exception) {
                        QueryResult.Error(e)
                }
        }

//        suspend fun fetchLocationByCoordinates(
//                latitude: Double,
//                longitude: Double,
//                apiKey: String
//        ): QueryResult<City> {
//                return try {
//                        val locations = api.getLocationName(
//                                latitude = latitude,
//                                longitude = longitude,
//                                apiKey = apiKey
//                        )
//                        val location = locations.firstOrNull() ?: throw Exception("No location found")
//                        QueryResult.Success(location)
//                } catch (e: Exception) {
//                        QueryResult.Error(e)
//                }
//        }
}