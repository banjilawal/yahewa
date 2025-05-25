package com.lawal.banji.yahewa.view.model

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.model.Coordinates
import com.lawal.banji.yahewa.model.CurrentWeatherState
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.repo.QueryResponseState
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.getRandomCity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class CurrentWeatherViewModel(private val repository: AppRepository) : ViewModel() {

    private val _currentWeatherState = MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Loading)
    val currentWeatherState: StateFlow<CurrentWeatherState> get() = _currentWeatherState

    private var _coordinates: MutableStateFlow<Coordinates?> = MutableStateFlow(null)
    val coordinates: StateFlow<Coordinates?> get() = _coordinates

    private var _zipCode: MutableStateFlow<String?> = MutableStateFlow(null)
    val zipCode: StateFlow<String?> get() = _zipCode

    private var _location: MutableStateFlow<Location?> = MutableStateFlow(null)
    val location: StateFlow<Location?> get() = _location

    private var previousCoordinates: Coordinates? = null // Cache for coordinates
    private var previousZipCode: String? = null // Cache for ZIP code

    init {
        viewModelScope.launch {
            val location = getRandomCity()
            val coordinates = location.coordinates
            queryCurrentWeatherByCoordinates(coordinates = coordinates, apiKey = AppDefault.API_KEY)
        }
    }

    fun updateLocation(location: Location) {
        _location.value= location
    }

    // Public method to set the zip code so the ViewModel can handle fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setZipcode(newZipCode: String) {
        if (previousZipCode == newZipCode) {
            println("ZIP code $newZipCode already queried. Skipping lookup.")
            return
        }

        _zipCode.value = newZipCode
        previousZipCode = newZipCode // Update cache for the ZIP code
        queryCurrentWeatherByZipCode(newZipCode, AppDefault.API_KEY)
    }

    // Public method to set the zip code so the ViewModel can handle fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setCoordinates(newCoordinates: Coordinates) {
        if (previousCoordinates == newCoordinates) {
            println("ZIP code $coordinates  already queried. Skipping lookup.")
            return
        }

        _coordinates.value = newCoordinates
        previousCoordinates = newCoordinates // Update cache for the ZIP code
        queryCurrentWeatherByCoordinates(coordinates = newCoordinates, apiKey= AppDefault.API_KEY)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun queryCurrentWeatherByZipCode(zipCode: String, apiKey: String) {
        if (previousZipCode == zipCode) {
            println("ZipCode $zipCode .longitude already queried. Skipping lookup.")
            return
        }

        setZipcode(newZipCode = zipCode)
        viewModelScope.launch {
            when (val queryResult = repository.requestCurrentWeatherByZipCode(zipCode, apiKey)) {
                is QueryResponseState.Success -> {
                    _currentWeatherState.value = CurrentWeatherState.Success(queryResult.data)
                }

                is QueryResponseState.Error -> {
                    _currentWeatherState.value =
                        CurrentWeatherState.Error(queryResult.exception.message ?: "Unknown Error")
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun queryCurrentWeatherByCoordinates(coordinates: Coordinates, apiKey: String) {
        if (previousCoordinates == coordinates) {
            println("Coordinates $coordinates.latitude, $coordinates.longitude already queried. Skipping lookup.")
            return
        }

       setCoordinates(newCoordinates = coordinates)// Update the coordinates cache
        viewModelScope.launch {
            when (val queryResult = repository.requestCurrentWeatherByCoordinates(coordinates = coordinates, apiKey = apiKey)) {
                is QueryResponseState.Success -> {
                    _currentWeatherState.value = CurrentWeatherState.Success(queryResult.data)
                    val city = queryResult.data.city
                    val coordinates = queryResult.data.coordinates
                    System.out.println("latitude:${coordinates.latitude} longitude:${coordinates.longitude} getting forecastRecords now")
                } is QueryResponseState.Error -> {
                    _currentWeatherState.value =
                        CurrentWeatherState.Error("Error: ${queryResult.exception.message}")
                }
            }
        }
    }
}