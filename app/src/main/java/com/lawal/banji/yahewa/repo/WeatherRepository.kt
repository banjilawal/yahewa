package com.lawal.banji.yahewa.repo

import com.lawal.banji.yahewa.query.RetrofitInstance.api
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.weather.model.WeatherRecord

class WeatherRepository {

        suspend fun fetchRecordByCoordinate(latitude: Double, longitude: Double, apiKey: String): WeatherRecord {
            return api.getWeatherData(latitude = latitude, longitude = longitude, apiKey = apiKey)
        }

    suspend fun fetchDefaultRecord(): WeatherRecord {
        return api.getWeatherData(latitude = AppDefault.LATITUDE, longitude = AppDefault.LONGITUDE, apiKey = AppDefault.API_KEY)
    }
}