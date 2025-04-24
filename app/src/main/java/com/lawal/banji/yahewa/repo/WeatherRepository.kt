package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.repo.RetrofitInstance.api
import com.lawal.banji.yahewa.weather.model.WeatherRecord

class WeatherRepository {

        suspend fun fetchRecordByCoordinate(latitude: Double, longitude: Double, apiKey: String): Result<WeatherRecord> {
                return try {
                        val response = api.getWeatherData(latitude = latitude, longitude = longitude, apiKey = apiKey)
                        Result.Success(response)
                } catch (e: Exception) {
                        Result.Error(e)
                }
        }
}