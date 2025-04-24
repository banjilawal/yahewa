package com.lawal.banji.yahewa.weather.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.repo.WeatherRepository
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.weather.model.WeatherRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _weatherRecord = MutableLiveData<WeatherRecord>()
    val weatherRecord: LiveData<WeatherRecord> get() = _weatherRecord

    init {
        viewModelScope.launch {
            fetchForDefaultLocation()
        }
    }

    private suspend fun fetchForDefaultLocation() {
        try {
            val response = repository.fetchRecordByCoordinate(
                latitude = AppDefault.LATITUDE,
                longitude = AppDefault.LONGITUDE,
                apiKey = AppDefault.API_KEY
            )
            _weatherRecord.postValue(response)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun fetchWeatherData(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchRecordByCoordinate(latitude = latitude, longitude = longitude, apiKey = apiKey)
                _weatherRecord.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

//  san diego lat:32.715736, long:-117.161087
// milwaukee lat:43.038902, long:-87.906471
//  lagos lat:6.5244, long:3.3792
//  new york lat:40.7128, long:-74.0060
//  london lat:51.5074, long:-0.1278
// tucson lat:32.22174, long:--110.92648
//  chicago lat:41.8781, long:-87.6298
// minneapolis lat:44.9778, long:-93.2650
// anchorage lat:61.2176, long:-149.8631
