package com.lawal.banji.yahewa.view.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.model.CityLookupState
import com.lawal.banji.yahewa.model.CurrentWeatherState
import com.lawal.banji.yahewa.model.ForecastGroupState
import com.lawal.banji.yahewa.repo.ForecastRepository
import com.lawal.banji.yahewa.repo.QueryResponseState
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.getRandomCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class ForecastViewModel(private val repository: ForecastRepository) : ViewModel() {

    private val _forecastGroupState =
        MutableStateFlow<ForecastGroupState>(ForecastGroupState.Loading)
    val forecastGroupState: StateFlow<ForecastGroupState> get() = _forecastGroupState

    private val _currentWeatherState = MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Loading)
    val currentWeatherState: StateFlow<CurrentWeatherState> get() = _currentWeatherState

    private var _zipcode: MutableStateFlow<String?> = MutableStateFlow(null)
    val zipcode: StateFlow<String?> get() = _zipcode

    private val _cityLookupState = MutableStateFlow<CityLookupState>(CityLookupState.Loading)
    val cityLookupState: StateFlow<CityLookupState> get() = _cityLookupState

    init {
        viewModelScope.launch {
            val location = getRandomCity()
//            println("Location: ${location.name} (${location.coordinates.latitude}, ${location.coordinates.longitude})")

            // Fetch the currentWeather
            fetchForecastByCoordinates(
                latitude = location.coordinates.latitude,
                longitude = location.coordinates.longitude,
                apiKey = AppDefault.API_KEY
            )
            fetchPredictionGroup(location.coordinates.latitude, location.coordinates.longitude, AppDefault.API_KEY)
        }
    }

    // Public method to set the zip code so the ViewModel can handle fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setZipcode(newZipcode: String) {
        if (newZipcode != _zipcode.value) {
            _zipcode.value = newZipcode
            fetchForecastByZipcode(newZipcode, AppDefault.API_KEY)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchForecastByCoordinates(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            when (val queryResult = repository.fetchByCoordinates(
                latitude = latitude,
                longitude = longitude,
                apiKey = apiKey
            )) {
                is QueryResponseState.Success -> {
                    _currentWeatherState.value = CurrentWeatherState.Success(queryResult.data)
                    fetchPredictionGroup(latitude, longitude, apiKey)
                }

                is QueryResponseState.Error -> {
                    _currentWeatherState.value =
                        CurrentWeatherState.Error("Error: ${queryResult.exception.message}")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchForecastByZipcode(zipcode: String, apiKey: String) {
        viewModelScope.launch {
            when (val queryResult = repository.fetchByZipcode(zipcode, apiKey)) {
                is QueryResponseState.Success -> {
                    _currentWeatherState.value = CurrentWeatherState.Success(queryResult.data)
                    val latitude = queryResult.data.coordinates.latitude
                    val longitude = queryResult.data.coordinates.longitude
                    fetchPredictionGroup(latitude, longitude, apiKey)
                }

                is QueryResponseState.Error -> {
                    _currentWeatherState.value =
                        CurrentWeatherState.Error(queryResult.exception.message ?: "Unknown Error")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchPredictionGroup(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            when (val queryResult = repository.fetchForecasts(
                longitude = longitude,
                latitude = latitude,
                count = AppDefault.NUMBER_OF_FORECASTS,
                apiKey = apiKey
            )
            ) {
                is QueryResponseState.Success -> {
                    _forecastGroupState.value = ForecastGroupState.Success(queryResult.data)
                    val country = queryResult.data.city.country
                    val sunset  = queryResult.data.predictions[0].sunset
                    val maxTemperature = queryResult.data.predictions[0].temperature.max
                    println("fetched for Country:$country maxTemp:$maxTemperature sunset:  $sunset")
                }

                is QueryResponseState.Error -> {
                    _forecastGroupState.value =
                        ForecastGroupState.Error("Failed to fetch currentWeather data: ${queryResult.exception.message}")
                }
            }
        }
    }
}
