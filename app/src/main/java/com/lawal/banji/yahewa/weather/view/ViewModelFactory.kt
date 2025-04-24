package com.lawal.banji.yahewa.weather.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lawal.banji.yahewa.repo.WeatherRepository

class WeatherViewModelFactory(
    private val repository: WeatherRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
