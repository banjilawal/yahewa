package com.lawal.banji.yahewa.weather.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.query.OpenWeatherResponse
import com.lawal.banji.yahewa.query.RetrofitInstance
import com.lawal.banji.yahewa.query.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<OpenWeatherResponse>()
    val weatherData: LiveData<OpenWeatherResponse> get() = _weatherData

    fun fetchWeatherData(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getWeatherData(latitude, longitude, apiKey = apiKey)
                    .execute()
                if (response.isSuccessful) {
                    _weatherData.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace();
            }
        }
    }

    fun fetchLocationName(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getLocationName(latitude, longitude, apiKey = apiKey).execute()
                if (response.isSuccessful && response.body() != null) {
                    val location = response.body()!![0].city
                    println("Location Name: $location")
                    // You can update LiveData or use the location name as needed
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}