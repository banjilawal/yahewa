package com.lawal.banji.yahewa.view.model

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.model.CityLookupState
import com.lawal.banji.yahewa.model.CurrentWeatherState
import com.lawal.banji.yahewa.model.ForecastGroupState
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.repo.QueryResponseState
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.getRandomCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class ForecastViewModel(private val repository: AppRepository) : ViewModel() {

    private val _forecastGroupState =
        MutableStateFlow<ForecastGroupState>(ForecastGroupState.Loading)
    val forecastGroupState: StateFlow<ForecastGroupState> get() = _forecastGroupState

    private val _currentWeatherState = MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Loading)
    val currentWeatherState: StateFlow<CurrentWeatherState> get() = _currentWeatherState

    private var _zipcode: MutableStateFlow<String?> = MutableStateFlow(null)
    val zipcode: StateFlow<String?> get() = _zipcode

    private var _location: MutableStateFlow<Location?> = MutableStateFlow(null)
    val location: StateFlow<Location?> get() = _location

    private val _cityLookupState = MutableStateFlow<CityLookupState>(CityLookupState.Loading)
    val cityLookupState: StateFlow<CityLookupState> get() = _cityLookupState

    private var lastQueriedCoordinates: Pair<Double, Double>? = null // Cache for coordinates
    private var lastQueriedZipCode: String? = null // Cache for ZIP code

    init {
        viewModelScope.launch {
            val location = getRandomCity()

            // Fetch the currentWeather
            fetchForecastByCoordinates(
                latitude = location.coordinates.latitude,
                longitude = location.coordinates.longitude,
                apiKey = AppDefault.API_KEY
            )
            fetchForecasts(location.coordinates.latitude, location.coordinates.longitude, AppDefault.API_KEY)
        }
    }

    fun updateLocation(location: Location) {
        _location.value= location
    }

    // Public method to set the zip code so the ViewModel can handle fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setZipcode(newZipcode: String) {
        if (newZipcode == lastQueriedZipCode) {
            println("ZIP code $newZipcode already queried. Skipping lookup.")
            return
        }

        _zipcode.value = newZipcode
        lastQueriedZipCode = newZipcode // Update cache for the ZIP code
        fetchForecastByZipcode(newZipcode, AppDefault.API_KEY)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchForecastByCoordinates(latitude: Double, longitude: Double, apiKey: String) {
        // Prevent a duplicate lookup by checking the cached coordinates
        if (lastQueriedCoordinates == Pair(latitude, longitude)) {
            println("Coordinates ($latitude, $longitude) already queried. Skipping lookup.")
            return
        }

        lastQueriedCoordinates = Pair(latitude, longitude) // Update the coordinates cache

        viewModelScope.launch {
            when (val queryResult = repository.fetchByCoordinates(
                latitude = latitude,
                longitude = longitude,
                apiKey = apiKey
            )) {
                is QueryResponseState.Success -> {
                    _currentWeatherState.value = CurrentWeatherState.Success(queryResult.data)
                    System.out.println("latitude:$latitude longitude:$longitude getting forecastRecords now")
                    fetchForecasts(latitude, longitude, apiKey)
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
                    System.out.println("fetched for Zipcode:$zipcode latitude:$latitude longitude:$longitude getting forecastRecords now")
                    fetchForecasts(latitude, longitude, apiKey)
                }

                is QueryResponseState.Error -> {
                    _currentWeatherState.value =
                        CurrentWeatherState.Error(queryResult.exception.message ?: "Unknown Error")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun fetchForecasts(latitude: Double, longitude: Double, apiKey: String) {
        viewModelScope.launch {
            when (val queryResult = repository.fetchForecastGroup(
                longitude = longitude,
                latitude = latitude,
                count = AppDefault.NUMBER_OF_FORECASTS,
                apiKey = apiKey
            )
            ) {
                is QueryResponseState.Success -> {
                    _forecastGroupState.value = ForecastGroupState.Success(queryResult.data)
                    val country = queryResult.data.city.country
                    val sunset  = queryResult.data.forecastRecords[0].sunset
                    val maxTemperature = queryResult.data.forecastRecords[0].temperature.max
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

