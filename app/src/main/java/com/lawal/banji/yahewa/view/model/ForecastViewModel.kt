package com.lawal.banji.yahewa.view.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.query.CityLookupState
import com.lawal.banji.yahewa.query.ForecastState
import com.lawal.banji.yahewa.query.PredictionGroupState
import com.lawal.banji.yahewa.query.QueryResult
import com.lawal.banji.yahewa.repo.ForecastRepository
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.convertLongToLocalDateTime
import com.lawal.banji.yahewa.utils.getRandomCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class ForecastViewModel(private val repository: ForecastRepository) : ViewModel() {

    private val _predictionGroupState =
        MutableStateFlow<PredictionGroupState>(PredictionGroupState.Loading)
    val predictionGroupState: StateFlow<PredictionGroupState> get() = _predictionGroupState

    private val _forecastState = MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> get() = _forecastState

    private var _zipcode: MutableStateFlow<String?> = MutableStateFlow(null)
    val zipcode: StateFlow<String?> get() = _zipcode

    private val _cityLookupState = MutableStateFlow<CityLookupState>(CityLookupState.Loading)
    val cityLookupState: StateFlow<CityLookupState> get() = _cityLookupState

    init {
        viewModelScope.launch {
            val location = getRandomCity()
//            println("Location: ${location.name} (${location.coordinates.latitude}, ${location.coordinates.longitude})")

            // Fetch the forecast
            fetchForecastByCoordinates(
                latitude = location.coordinates.latitude,
                longitude = location.coordinates.longitude,
                apiKey = AppDefault.API_KEY
            )
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
                is QueryResult.Success -> {
                    _forecastState.value = ForecastState.Success(queryResult.data)
                    fetchPredictionGroup(latitude, longitude, apiKey)
                }

                is QueryResult.Error -> {
                    _forecastState.value =
                        ForecastState.Error("Error: ${queryResult.exception.message}")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchForecastByZipcode(zipcode: String, apiKey: String) {
        viewModelScope.launch {
            when (val queryResult = repository.fetchByZipcode(zipcode, apiKey)) {
                is QueryResult.Success -> {
                    _forecastState.value = ForecastState.Success(queryResult.data)
                    val latitude = queryResult.data.coordinates.latitude
                    val longitude = queryResult.data.coordinates.longitude
                    fetchForecastList(latitude, longitude, apiKey)
                }

                is QueryResult.Error -> {
                    _forecastState.value =
                        ForecastState.Error(queryResult.exception.message ?: "Unknown Error")
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
                is QueryResult.Success -> {
                    _predictionGroupState.value = PredictionGroupState.Success(queryResult.data)
                    val country = queryResult.data.city.country
                    val sunset  = convertLongToLocalDateTime(queryResult.data.forecastList[0].sunset)
                    val maxTemperature = queryResult.data.forecastList[0].temperatures.max
                    println("fetched for Country:$country maxTemp:$maxTemperature sunset:  $sunset")
                }

                is QueryResult.Error -> {
                    _predictionGroupState.value =
                        PredictionGroupState.Error("Failed to fetch forecast data: ${queryResult.exception.message}")
                }
            }
        }
    }
}
