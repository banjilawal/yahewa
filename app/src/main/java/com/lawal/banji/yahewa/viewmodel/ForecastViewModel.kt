package com.lawal.banji.yahewa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.repo.ForecastRepository
import com.lawal.banji.yahewa.repo.QueryResult
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.randomLocation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ForecastViewModel(private val repository: ForecastRepository) : ViewModel() {

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> get() = _forecastState


    init {
        viewModelScope.launch {
            val location = randomLocation()
            println("Location: ${location.name} (${location.latitude}, ${location.longitude})")
            fetchForecastByCoordinates(
                latitude = AppDefault.LATITUDE,
                longitude = AppDefault.LONGITUDE,
                apiKey = AppDefault.API_KEY
            )
        }
    }

    private fun fetchForecastByCoordinates(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            when (val queryResult = repository.fetchByCoordinates(
                latitude = latitude,
                longitude = longitude,
                apiKey = apiKey
            )) {
                is QueryResult.Success -> _forecastState.value = ForecastState.Success(queryResult.data)
                is QueryResult.Error -> _forecastState.value = ForecastState.Error("Error: ${queryResult.exception.message}")
            }
        }
    }
}
