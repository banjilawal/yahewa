package com.lawal.banji.yahewa.view.model

import android.location.Location
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.model.CityLookupState
import com.lawal.banji.yahewa.model.Coordinates
import com.lawal.banji.yahewa.model.ForecastState
import com.lawal.banji.yahewa.model.GeoCoding
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

    private var _coordinatesState: MutableStateFlow<Coordinates?> = MutableStateFlow(null)
    val coordinates: StateFlow<Coordinates?> get() = _coordinatesState

    private var _zipCodeState: MutableStateFlow<String?> = MutableStateFlow(null)
    val zipCode: StateFlow<String?> get() = _zipCodeState

    private var _geoCodingState: MutableStateFlow<GeoCoding?> = MutableStateFlow(null)
    val goeCodingState:  StateFlow<GeoCoding?> get() = _geoCodingState

    private var _locationState: MutableStateFlow<Location?> = MutableStateFlow(null)
    val location: StateFlow<Location?> get() = _locationState

    private val _cityLookupState = MutableStateFlow<CityLookupState>(CityLookupState.Loading)
    val cityLookupState: StateFlow<CityLookupState> get() = _cityLookupState

    private var previousCoordinates: Coordinates? = null // Cache for coordinates
    private var previousZipCode: String? = null // Cache for ZIP code

    init {
        viewModelScope.launch {
            val city = getRandomCity()
            val coordinates = city.coordinates
            queryByCoordinates(coordinates = coordinates , apiKey = AppDefault.API_KEY)
        }
    }

    fun updateLocation(location: Location) {
        _locationState.value= location
    }

    // Public method to set the zip code so the ViewModel can handle fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setZipcode(newZipCode: String) {
//        if (previousZipCode == newZipCode) {
//            println("ZIP code $newZipCode already queried. Skipping lookup.")
//            return
//        }

        _zipCodeState.value = newZipCode
        previousZipCode = newZipCode // Update cache for the ZIP code
        System.out.println("ForecastViewModel .setCoordinates has updated the coordinates to $newZipCode")
        forecastByZipCodeHelper(newZipCode, AppDefault.API_KEY)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setCoordinates(newCoordinates: Coordinates) {
//        if (previousCoordinates == newCoordinates) {
//            println("$newCoordinates  already queried. Skipping lookup.")
//            return
//        }

        _coordinatesState.value = newCoordinates
        previousCoordinates = newCoordinates // Update cache for the ZIP code
        queryByCoordinates(coordinates = newCoordinates, apiKey= AppDefault.API_KEY)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun queryByZipCode(zipCode: String, apiKey: String) {
//        if (previousZipCode == zipCode) {
//            println("ZipCode $zipCode already  queried. Skipping lookup.")
//            return
//        }
//        setZipcode(newZipCode = zipCode)
        System.out.println("ForecastViewModel is going to call repository.requestForecastByZipCode with zipcode $zipCode")
        forecastByZipCodeHelper(zipCode, apiKey)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun forecastByZipCodeHelper(zipCode: String, apiKey: String) {
        System.out.println("ZipCodeHelper is  calling repository.requestGeoCodingByZipCode with  $zipCode")
        viewModelScope.launch {
            when (val queryResult = repository.requestGeoCodingByZipCode(zipCode, apiKey = apiKey)) {
                is QueryResponseState.Success -> {
                    _geoCodingState.value = queryResult.data
                    System.out.println("ZipCodeHelper received geoCoding data  $_geoCodingState.value?")
                    val latitude = queryResult.data.latitude
                    val longitude = queryResult.data.longitude
                    System.out.println("ForecastViewModel received longitude $longitude and latitude $latitude for zip code  $zipCode")
                    setCoordinates(Coordinates(latitude = latitude, longitude = longitude))
                } is QueryResponseState.Error -> {
                _forecastState.value =
                    ForecastState.Error("Failed to fetch currentWeather data: ${queryResult.exception.message}")
            }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun queryByCoordinates(coordinates: Coordinates, apiKey: String) {
        System.out.println("ForecastViewModel is getting the forecast  for coordinates  $coordinates")
        viewModelScope.launch {
            when (val queryResult = repository.requestForecastByCoordinates(
                coordinates = coordinates,
                numberOfRecords = AppDefault.NUMBER_OF_FORECASTS,
                apiKey = apiKey
            )
            ) {
                is QueryResponseState.Success -> {
                    _forecastState.value = ForecastState.Success(queryResult.data)
                    val cityName = queryResult.data.city.name
                    val country = queryResult.data.city.country
                    val sunset  = queryResult.data.forecastRecords[0].sunset
                    val maxTemperature = queryResult.data.forecastRecords[0].temperature.max
                    System.out.println("ForecastViewModel  successfully   got  $cityName forecastRecords for country $country with sunset at $sunset and max temperature of $maxTemperature")
                } is QueryResponseState.Error -> {
                    _forecastState.value =
                        ForecastState.Error("Failed to fetch forecast data: ${queryResult.exception.message}")
                }
            }
        }
    }
}

