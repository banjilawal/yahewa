package com.lawal.banji.yahewa

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.RetrofitInstance.api
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
}