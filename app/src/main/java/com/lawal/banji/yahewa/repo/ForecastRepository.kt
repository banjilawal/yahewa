package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.repo.RetrofitInstance.api
import com.lawal.banji.yahewa.weather.model.Forecast

class ForecastRepository {

        suspend fun fetchRecordByCoordinate(
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
}