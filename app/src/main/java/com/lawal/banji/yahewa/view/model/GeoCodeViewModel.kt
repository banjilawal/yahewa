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

    private var _geoCodeState : MutableStateFlow<GeoCodeState?> = MutableStateFlow<GeoCodeState?>(GeoCodeState.Loading)
    val geoCodeState: StateFlow<GeoCodeState?> get() = _geoCodeState

    private var _currentWeatherState: MutableStateFlow<CurrentWeatherState?> =
        MutableStateFlow<CurrentWeatherState?>(CurrentWeatherState.Loading)
    val currentWeatherState: StateFlow<CurrentWeatherState?> get() = _currentWeatherState

    private var _forecastState: MutableStateFlow<ForecastState?> = MutableStateFlow<ForecastState?>(ForecastState.Loading)
    val forecastState: StateFlow<ForecastState?> get() = _forecastState

    fun loadData(coordinate: Coordinate, apiKey: String) {
        viewModelScope.launch {
            fetchCurrentWeatherByCoordinate(coordinate = coordinate, apiKey = apiKey)
            fetchForecastByCoordinate(coordinate = coordinate, numberOfRecords = 5, apiKey = apiKey)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchCurrentWeatherByCoordinate(coordinate: Coordinate, apiKey: String) {
//        if (coordinate == (_geoCodeState.value as GeoCodeState.Success).geoCode.coordinate) {
//            println("Weather for coordinate $coordinate  already queried. Skipping lookup.")
//        }
       currentWeatherQueryResponseHandler( repository.requestCurrentWeatherByCoordinate(coordinate = coordinate, apiKey = apiKey))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun fetchCurrentWeatherByZipCode(zipCode: String, apiKey: String) {
//        if (zipCode== (_geoCodeState.value as GeoCodeState.Success).geoCode.zipCode) {
//            println("Weather for coordinate $zipCode already queried. Skipping lookup.")
//        }
        currentWeatherQueryResponseHandler(repository.requestCurrentWeatherByZipCode(zipCode = zipCode, apiKey = apiKey))
    }

    private fun currentWeatherQueryResponseHandler(queryResponseState: QueryResponseState<CurrentWeather>) {
        when (queryResponseState) {
            is QueryResponseState.Success -> {
                val currentWeather = queryResponseState.data
                _currentWeatherState.value = CurrentWeatherState.Success(currentWeather = currentWeather)
                _geoCodeState.value = GeoCodeState.Success(
                    geoCode = GeoCode(
                        name = currentWeather.cityName,
                        country = currentWeather.sys.country,
                        latitude = currentWeather.coordinate.latitude,
                        longitude = currentWeather.coordinate.longitude,
                    )
                )
                println("Current weather for ${currentWeather.cityName}, ${currentWeather.sys.country} is $currentWeather")
            }
            is QueryResponseState.Error -> {
                val errorMessage = "Error fetching current weather: ${queryResponseState.exception.message}"
                _currentWeatherState.value = CurrentWeatherState.Error(errorMessage)
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }

    suspend fun fetchForecastByCoordinate(coordinate: Coordinate, numberOfRecords: Int, apiKey:String) {
//        if (coordinate == (_geoCodeState.value as GeoCodeState.Success).geoCode.coordinate) {
//            println("Forecast for coordinate $coordinate already queried. Skipping lookup.")
//        }
        forecastQueryResponseHandler(repository.requestForecastByCoordinates(coordinate, numberOfRecords, apiKey))
    }

    suspend fun fetchForecastByZipCode(zipCode: String, numberOfRecords: Int, apiKey:String) {
//        if (zipCode== (_geoCodeState.value as GeoCodeState.Success).geoCode.zipCode) {
//            println("Forecast for coordinate $zipCode already queried. Skipping lookup.")
//        }

        when (val respomse = repository.requestGeoCodeByZipCode(zipCode = zipCode, apiKey = apiKey)) {
            is QueryResponseState.Success -> {
                _geoCodeState.value = GeoCodeState.Success(respomse.data)
                fetchForecastByCoordinate(respomse.data.coordinate, numberOfRecords, apiKey)
            } is QueryResponseState.Error -> {
                val errorMessage = "Error fetching geo code by zip code: ${respomse.exception.message}"
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
                        country = forecast.city.country,
                        latitude = forecast.city.coordinate.latitude,
                        longitude = forecast.city.coordinate.longitude,
                    )
                )
                println("$forecast.forecastRecords.size day forecast for $forecast.city.name, $forecast.city.country")
            }
            is QueryResponseState.Error -> {
                val errorMessage = "Error fetching forecast ${queryResponseState.exception.message}"
                _forecastState.value = ForecastState.Error(errorMessage)
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }
}


