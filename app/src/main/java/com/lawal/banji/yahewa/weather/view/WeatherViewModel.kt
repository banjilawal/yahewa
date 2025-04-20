package com.lawal.banji.yahewa.weather.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.query.RetrofitInstance.api
import com.lawal.banji.yahewa.weather.model.WeatherRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<WeatherRecord>()
    val weatherData: LiveData<WeatherRecord> get() = _weatherData

    fun initializeWeatherData() {
        fetchWeatherData(
            latitude = 61.2176,
            longitude = -149.8631,
            apiKey = "43d92973340fa3166680bbe3af8d3943"
        )
    }

    fun fetchWeatherData(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getWeatherData(latitude, longitude, apiKey = apiKey)
                _weatherData.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

//  san diego lat:32.715736, long:-117.161087
//  lagos lat:6.5244, long:3.3792
//  new york lat:40.7128, long:-74.0060
//  london lat:51.5074, long:-0.1278
// tucson lat:32.22174, long:--110.92648
//  chicago lat:41.8781, long:-87.6298
// minneapolis lat:44.9778, long:-93.2650
// anchorage lat:61.2176, long:-149.8631
//class WeatherViewModel : ViewModel() {
//    private val _weatherData = MutableLiveData<WeatherRecord>()
//    val weatherData: LiveData<WeatherRecord> get() = _weatherData
//
//
//    fun fetchWeatherData(latitude: Double, longitude: Double, apiKey: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = api.getWeatherData(latitude, longitude, apiKey = apiKey)
//                _weatherData.postValue(response)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//}

//    @GET("geo/1.0/reverse")
//    suspend fun getLocationName(
//        @Query("lat") latitude: Double,
//        @Query("lon") longitude: Double,
//        @Query("limit") limit: Int = 1,
//        @Query("appid") apiKey: String
//    ): Call<List<ReverseGeocodingResponse>>
//    }
//}
//    fun fetchLocationName(latitude: Double, longitude: Double, apiKey: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val response = RetrofitInstance.api.getLocationName(latitude, longitude, apiKey = apiKey).execute()
//                if (response.isSuccessful && response.body() != null) {
//                    val location = response.body()!![0].city
//                    println("Location Name: $location")
//                    // You can update LiveData or use the location name as needed
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//}