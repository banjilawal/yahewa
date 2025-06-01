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

    private var currentZipCode: String? = null
    private var currentCoordinate: Coordinate? = null

    fun loadDataByCoordinate(coordinate: Coordinate) {
        if (coordinate == currentCoordinate) {
            println("loadDataByCoordinate: Coordinate $coordinate already queried. Skipping lookup.")
            return
        }

        currentZipCode = null
        currentCoordinate = coordinate
        println("loadDataByCoordinate: $coordinate")
        viewModelScope.launch {
            currentWeatherQueryResponseHandler(repository.requestCurrentWeatherByCoordinate(coordinate = coordinate))
            forecastQueryResponseHandler(repository.requestForecastByCoordinate(coordinate = coordinate))
        }
    }

    fun loadDataByZipCode(zipCode: String) {
        if (zipCode == currentZipCode) {
            println("loadDataByZipCode: Zip code $zipCode already queried. Skipping lookup.")
            return
        }

        currentCoordinate = null
        currentZipCode = zipCode
        println("loadDataByZipCode: $zipCode")
        viewModelScope.launch {
            currentWeatherQueryResponseHandler(repository.requestCurrentWeatherByZipCode(zipCode = zipCode),)
            forecastQueryResponseHandler(repository.requestForecastByCoordinate(coordinate = currentCoordinate!!))
        }
    }

    private suspend  fun  currentWeatherQueryResponseHandler(queryResponseState: QueryResponseState<CurrentWeather>) {
        when (queryResponseState) {
            is QueryResponseState.Success -> {
                val currentWeather = queryResponseState.data
                _currentWeatherState.value = CurrentWeatherState.Success(currentWeather = currentWeather)
                updateGeoCodeFromWeather(currentWeather)

                println("currentWeatherResponseHandler set geoCode to: ${(_geoCodeState.value as GeoCodeState.Success).geoCode.toString()}")
                println("Current weather for ${currentWeather.cityName}, ${currentWeather.sys.country} is $currentWeather")
            } is QueryResponseState.Error -> {
                val errorMessage = "Error fetching current weather: ${queryResponseState.exception.message}"
                _currentWeatherState.value = CurrentWeatherState.Error(errorMessage)
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }

   private suspend  fun forecastQueryResponseHandler(queryResponseState: QueryResponseState<Forecast>) {
        when (queryResponseState) {
            is QueryResponseState.Success -> {
                val forecast = queryResponseState.data
                _forecastState.value = ForecastState.Success(forecast = forecast)
                println("$forecast.forecastRecords.size day forecast for $forecast.city.name, $forecast.city.country")
            } is QueryResponseState.Error -> {
                val errorMessage = "Error fetching forecast ${queryResponseState.exception.message}"
                _forecastState.value = ForecastState.Error(errorMessage)
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }

    private suspend fun updateGeoCodeFromWeather(currentWeather: CurrentWeather)  {
        val geoCode: GeoCode = GeoCode(
            name = currentWeather.cityName,
            country = currentWeather.sys.country,
            latitude = currentWeather.coordinate.latitude,
            longitude = currentWeather.coordinate.longitude,
            timezone = currentWeather.timezone,
            zipCode = currentZipCode
        )
        currentCoordinate = currentWeather.coordinate
        _geoCodeState.value = GeoCodeState.Success(geoCode = geoCode)
        stateGeoCodeLookup()
    }

    private suspend fun  stateGeoCodeLookup() {
        val response =repository.requestReverseGeoCode(coordinate = (_geoCodeState.value as GeoCodeState.Success).geoCode.coordinate)
        println("state lookup response $response")
        when (response) {
            is QueryResponseState.Success<List<GeoCode>> -> {
                val newState = response.data.first().state
                (_geoCodeState.value as GeoCodeState.Success).geoCode.state = newState
            } is QueryResponseState.Error -> {
                val errorMessage = "Error fetching US state: ${response.exception.message}"
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }
}