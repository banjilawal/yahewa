package com.lawal.banji.yahewa.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lawal.banji.yahewa.repo.ForecastRepository

class WeatherViewModelFactory(
    private val repository: ForecastRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
