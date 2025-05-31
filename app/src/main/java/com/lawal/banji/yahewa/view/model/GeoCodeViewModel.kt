package com.lawal.banji.yahewa.view.model
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.model.Coordinate
import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.model.CurrentWeatherState
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.ForecastState
import com.lawal.banji.yahewa.model.GeoCode
import com.lawal.banji.yahewa.model.GeoCodeState
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.repo.QueryResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class GeoCodeViewModel(private val repository: AppRepository) : ViewModel() {

    private var _geoCodeState: MutableStateFlow<GeoCodeState?> =
        MutableStateFlow<GeoCodeState?>(GeoCodeState.Loading)
    val geoCodeState: StateFlow<GeoCodeState?> get() = _geoCodeState

    private var _currentWeatherState: MutableStateFlow<CurrentWeatherState?> =
        MutableStateFlow<CurrentWeatherState?>(CurrentWeatherState.Loading)
    val currentWeatherState: StateFlow<CurrentWeatherState?> get() = _currentWeatherState

    private var _forecastState: MutableStateFlow<ForecastState?> =
        MutableStateFlow<ForecastState?>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState?> get() = _forecastState

    fun loadDataByCoordinate(coordinate: Coordinate) {
        println("loadDataByCoordinate: $coordinate")
        viewModelScope.launch {
            fetchCurrentWeatherByCoordinate(coordinate = coordinate,)
            fetchForecastByCoordinate(coordinate = coordinate)
            findUSState()

            // Only update if the coordinate has changed
//                _geoCodeState.value = GeoCodeState.Loading
//                updateCityStateByCoordinate(coordinate = coordinate)
//            } else {
//                println("loadDataByCoordinate: GeoCodeState already has this coordinate, skipping reverse geocode.")
//            }

        }
    }

    fun loadDataByZipCode(zipCode: String?, apiKey: String) {
        if (zipCode.isNullOrEmpty()) {
            println("loadDataByZipCode: Zip code is null or empty, using coordinate from GeoCodeState instead")
            loadDataByCoordinate(
                coordinate = (_geoCodeState.value as GeoCodeState.Success).geoCode.coordinate
            )
        } else {
            println("loadDataByZipCode: $zipCode")
            viewModelScope.launch {
                fetchCurrentWeatherByZipCode(zipCode = zipCode)
                fetchForecastByZipCode(zipCode = zipCode,)
                findUSState()
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchCurrentWeatherByCoordinate(coordinate: Coordinate) {
//        if (coordinate == (_geoCodeState.value as GeoCodeState.Success).geoCode.coordinate) {
//            println("Weather for coordinate $coordinate  already queried. Skipping lookup.")
//        }
        println("insde fetchCurrentWeatherByCoordinate: $coordinate")
//        val geoCode = (_geoCodeState.value as GeoCodeState.Success).geoCode
//        println("fetching current  weather for ${geoCode.toString()}")
        currentWeatherQueryResponseHandler(
            repository.requestCurrentWeatherByCoordinate(coordinate = coordinate),
            zipCode = null
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchCurrentWeatherByZipCode(zipCode: String?) {
//        if (zipCode== (_geoCodeState.value as GeoCodeState.Success).geoCode.zipCode) {
//            println("Weather for coordinate $zipCode already queried. Skipping lookup.")
//        }
        if (zipCode.isNullOrEmpty()) {
            println("fetchCurrentWeatherByZipCode: Zip code is null or empty, using coordinate from GeoCodeState instead")
            currentWeatherQueryResponseHandler(
                repository.requestCurrentWeatherByCoordinate(coordinate = (_geoCodeState.value as GeoCodeState.Success).geoCode.coordinate),
                null
            )
        } else {
            println("fetchCurrentWeatherByZipCode: $zipCode")
            currentWeatherQueryResponseHandler(
                repository.requestCurrentWeatherByZipCode(zipCode = zipCode,),
                zipCode = zipCode
            )
        }
    }

    private fun currentWeatherQueryResponseHandler(
        queryResponseState: QueryResponseState<CurrentWeather>,
        zipCode: String?
    ) {
        when (queryResponseState) {
            is QueryResponseState.Success -> {
                val currentWeather = queryResponseState.data
                _currentWeatherState.value =
                    CurrentWeatherState.Success(currentWeather = currentWeather)
                _geoCodeState.value = GeoCodeState.Success(
                    geoCode = GeoCode(
                        name = currentWeather.cityName,
                        country = currentWeather.sys.country,
                        latitude = currentWeather.coordinate.latitude,
                        longitude = currentWeather.coordinate.longitude,
                        zipCode = zipCode
                    )
                )
                println("currentWeatherResponseHandler set geoCode to: ${(_geoCodeState.value as GeoCodeState.Success).geoCode.toString()}")
                println("Current weather for ${currentWeather.cityName}, ${currentWeather.sys.country} is $currentWeather")
            }

            is QueryResponseState.Error -> {
                val errorMessage =
                    "Error fetching current weather: ${queryResponseState.exception.message}"
                _currentWeatherState.value = CurrentWeatherState.Error(errorMessage)
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }

    suspend fun fetchForecastByCoordinate(coordinate: Coordinate) {
//        if (coordinate == (_geoCodeState.value as GeoCodeState.Success).geoCode.coordinate) {
//            println("Forecast for coordinate $coordinate already queried. Skipping lookup.")
//        }
        forecastQueryResponseHandler(repository.requestForecastByCoordinate(coordinate = coordinate))
    }

    suspend fun fetchForecastByZipCode(zipCode: String) {
//        if (zipCode== (_geoCodeState.value as GeoCodeState.Success).geoCode.zipCode) {
//            println("Forecast for coordinate $zipCode already queried. Skipping lookup.")
//        }

        when (val response = repository.requestGeoCodeByZipCode(zipCode = zipCode)) {
            is QueryResponseState.Success -> {
                _geoCodeState.value = GeoCodeState.Success(response.data)
                fetchForecastByCoordinate(response.data.coordinate)
            }

            is QueryResponseState.Error -> {
                val errorMessage =
                    "Error fetching geo code by zip code: ${response.exception.message}"
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
                _forecastState.value = ForecastState.Error(errorMessage)
            }
        }
    }

    private fun forecastQueryResponseHandler(queryResponseState: QueryResponseState<Forecast>) {
        when (queryResponseState) {
            is QueryResponseState.Success -> {
                val forecast = queryResponseState.data
                _forecastState.value = ForecastState.Success(forecast = forecast)
                _geoCodeState.value = GeoCodeState.Success(
                    geoCode = GeoCode(
                        name = forecast.city.name,
                        country = forecast.city.country.toString(),
                        latitude = forecast.city.coordinate.latitude,
                        longitude = forecast.city.coordinate.longitude,
                    )
                )
                println("forecastQueryResponseHandler set geoCode to: ${(_geoCodeState.value as GeoCodeState.Success).geoCode.toString()}")
                println("$forecast.forecastRecords.size day forecast for $forecast.city.name, $forecast.city.country")
            }

            is QueryResponseState.Error -> {
                val errorMessage = "Error fetching forecast ${queryResponseState.exception.message}"
                _forecastState.value = ForecastState.Error(errorMessage)
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }

    private suspend fun findUSState() {
        println("Inside findUSState")

        val country: String =
            (_geoCodeState.value as GeoCodeState.Success).geoCode.country.toString()
        println("findUSState: country is $country")

        if ((_geoCodeState.value as GeoCodeState.Success).geoCode.state != null && country.uppercase() == "US") {
            println("findUSState: country is US, state is not null")
            val response =repository.requestReverseGeoCode(coordinate = (_geoCodeState.value as GeoCodeState.Success).geoCode.coordinate)
            println("findUSState: response is $response")
            when (response) {
                is QueryResponseState.Success<List<GeoCode>> -> {
                    val geoCodes = response.data // This is List<GeoCode>
                    println(geoCodes)
                    val newState = geoCodes.first().state
                    (_geoCodeState.value as GeoCodeState.Success).geoCode.state = newState
                } is QueryResponseState.Error -> {
                    val errorMessage = "Error fetching US state: ${response.exception.message}"
                    _geoCodeState.value = GeoCodeState.Error(errorMessage)
                }
            }
        }
    }
}