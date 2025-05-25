package com.lawal.banji.yahewa.view.model

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.model.CityLookupState
import com.lawal.banji.yahewa.model.Coordinates
import com.lawal.banji.yahewa.model.ForecastState
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.repo.QueryResponseState
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.getRandomCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class ForecastViewModel(private val repository: AppRepository) : ViewModel() {

    private val _forecastState =
        MutableStateFlow<ForecastState>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState> get() = _forecastState

    private var _coordinates: MutableStateFlow<Coordinates?> = MutableStateFlow(null)
    val coordinates: StateFlow<Coordinates?> get() = _coordinates

    private var _zipcode: MutableStateFlow<String?> = MutableStateFlow(null)
    val zipcode: StateFlow<String?> get() = _zipcode

    private var _location: MutableStateFlow<Location?> = MutableStateFlow(null)
    val location: StateFlow<Location?> get() = _location

    private val _cityLookupState = MutableStateFlow<CityLookupState>(CityLookupState.Loading)
    val cityLookupState: StateFlow<CityLookupState> get() = _cityLookupState

    private var previousCoordinates: Coordinates? = null // Cache for coordinates
    private var previousZipCode: String? = null // Cache for ZIP code

    init {
        viewModelScope.launch {
            val location = getRandomCity()
            val coordinates = location.coordinates
            queryByCoordinates(coordinates = coordinates , apiKey = AppDefault.API_KEY)
        }
    }

    fun updateLocation(location: Location) {
        _location.value= location
    }

    // Public method to set the zip code so the ViewModel can handle fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setZipcode(newZipcode: String) {
        if (newZipcode == previousZipCode) {
            println("ZIP code $newZipcode already queried. Skipping lookup.")
            return
        }
//
//        _zipcode.value = newZipcode
//        lastQueriedZipCode = newZipcode // Update cache for the ZIP code
//        fetchForecastByZipcode(newZipcode, AppDefault.API_KEY)
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun fetchForecastByZipcode(zipcode: String, apiKey: String) {
//        viewModelScope.launch {
//            when (val queryResult = repository.fetchByZipcode(zipcode, apiKey)) {
//                is QueryResponseState.Success -> {
//                    val latitude = queryResult.data.coordinates.latitude
//                    val longitude = queryResult.data.coordinates.longitude
//                    System.out.println("fetched for Zipcode:$zipcode latitude:$latitude longitude:$longitude getting forecastRecords now")
//                    queryForecastByCoordinates(latitude, longitude, apiKey)
//                }
//
//                is QueryResponseState.Error -> {
//                    _currentWeatherState.value =
//                        CurrentWeatherState.Error(queryResult.exception.message ?: "Unknown Error")
//                }
//            }
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCoordinates(newCoordinates: Coordinates) {
        if (previousCoordinates == newCoordinates) {
            println("$newCoordinates  already queried. Skipping lookup.")
            return
        }

        _coordinates.value = newCoordinates
        previousCoordinates = newCoordinates // Update cache for the ZIP code
        queryByCoordinates(coordinates = newCoordinates, apiKey= AppDefault.API_KEY)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun queryByCoordinates(coordinates: Coordinates, apiKey: String) {
        viewModelScope.launch {
            when (val queryResult = repository.requestForecastByCoordinates(
                coordinates = coordinates,
                numberOfRecords = AppDefault.NUMBER_OF_FORECASTS,
                apiKey = apiKey
            )
            ) {
                is QueryResponseState.Success -> {
                    _forecastState.value = ForecastState.Success(queryResult.data)
                    val country = queryResult.data.city.country
                    val sunset  = queryResult.data.forecastRecords[0].sunset
                    val maxTemperature = queryResult.data.forecastRecords[0].temperature.max
                    println("fetched for Country:$country maxTemp:$maxTemperature sunset:  $sunset")
                } is QueryResponseState.Error -> {
                    _forecastState.value =
                        ForecastState.Error("Failed to fetch currentWeather data: ${queryResult.exception.message}")
                }
            }
        }
    }
}

