package com.lawal.banji.yahewa.view.model
import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.model.Coordinate
import com.lawal.banji.yahewa.model.CurrentWeather
import com.lawal.banji.yahewa.model.CurrentWeatherState
import com.lawal.banji.yahewa.model.ExpirationTimer
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.model.ForecastState
import com.lawal.banji.yahewa.model.GeoCode
import com.lawal.banji.yahewa.model.GeoCodeState
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.repo.QueryResponseState
import com.lawal.banji.yahewa.utils.AppDefault.CACHE_LIFETIME_MILLISECONDS
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

    private var currentCityName: String? = null
    private var currentZipCode: String? = null
    private var currentCoordinate: Coordinate? = null

    private var cachedCurrentWeather: CurrentWeather? = null
    private var cachedForecast: Forecast? = null
    private val expirationTimer: ExpirationTimer = ExpirationTimer(
        millisecondsDuration = CACHE_LIFETIME_MILLISECONDS,
        onTickCallback = { remainingTime ->
//            println("Cache valid for ${remainingTime / MILLISECONDS_PER_SECOND} seconds")
        },
        onFinishCallback = { isCacheValid = false }
    )
    private var countdownTimer: CountDownTimer? = null
    private var isCacheValid: Boolean = false

    fun loadDataByCoordinate(coordinate: Coordinate) {
        if (currentCoordinate == coordinate && isCacheValid) {
//            println("LOAD_DATA_BY_COORDINATE Coordinate" +
//                    " $coordinate already queried. SENDING CACHED DATE.")
            return
        }

        resetCache()
        currentCoordinate = coordinate
//        println("loadDataByCoordinate: $coordinate")
        viewModelScope.launch {
            currentWeatherQueryResponseHandler(
                repository.requestCurrentWeatherByCoordinate(
                    coordinate = coordinate
                )
            )
            forecastQueryResponseHandler(repository.requestForecastByCoordinate(coordinate = coordinate))
            printInfo()
        }
    }

    fun loadDataByZipCode(zipCode: String) {
        if (currentZipCode != null && currentZipCode!!.isNotEmpty() && currentZipCode == zipCode && isCacheValid()) {
//            println("LOAD_DATA_BY_ZIP Zip code $zipCode already queried. Sending cached DATA.")
            return
        }

        resetCache()
        currentZipCode = zipCode
//        println("loadDataByZipCode: $zipCode")
        viewModelScope.launch {
            currentWeatherQueryResponseHandler(repository.requestCurrentWeatherByZipCode(zipCode = zipCode),)
            forecastQueryResponseHandler(repository.requestForecastByCoordinate(coordinate = currentCoordinate!!))
            printInfo()
        }
    }

    fun loadDataByCityName(cityName: String) {
        if (currentCityName == cityName && isCacheValid()) {
//            println("LOAD_DATA_BY_CITY NAME: $cityName already queried. SENDING CACHED_DATA.")
            return
        }

        resetCache()
        currentCityName = cityName
//        println("loadDataByCityName: $cityName")
        viewModelScope.launch {
            currentCoordinate = coordinateFromCityName(cityName = cityName)
            currentWeatherQueryResponseHandler(
                repository.requestCurrentWeatherByCoordinate(
                    coordinate = currentCoordinate!!
                )
            )
            forecastQueryResponseHandler(repository.requestForecastByCoordinate(coordinate = currentCoordinate!!))
           printInfo()
        }
    }

    private fun printInfo() {
        println("${(_geoCodeState.value as GeoCodeState.Success).geoCode} $cachedCurrentWeather\nForecast\n$cachedForecast")
    }

    private suspend fun currentWeatherQueryResponseHandler(queryResponseState: QueryResponseState<CurrentWeather>) {
        when (queryResponseState) {
            is QueryResponseState.Success -> {
                val currentWeather = queryResponseState.data
                cachedCurrentWeather = currentWeather
                _currentWeatherState.value =
                    CurrentWeatherState.Success(currentWeather = currentWeather)
                updateGeoCodeFromWeather(currentWeather)

//                println("current weather for  " +
//                        "${(_geoCodeState.value as GeoCodeState.Success).geoCode}\n$currentWeather"
//                )
            }

            is QueryResponseState.Error -> {
                val errorMessage =
                    "Error fetching current weather: ${queryResponseState.exception.message}"
                _currentWeatherState.value = CurrentWeatherState.Error(errorMessage)
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }

    private suspend fun forecastQueryResponseHandler(queryResponseState: QueryResponseState<Forecast>) {
        when (queryResponseState) {
            is QueryResponseState.Success -> {
                val forecast = queryResponseState.data
                _forecastState.value = ForecastState.Success(forecast = forecast)
//                println("$forecast")
                cachedForecast = forecast
            }

            is QueryResponseState.Error -> {
                val errorMessage = "Error fetching forecast ${queryResponseState.exception.message}"
                _forecastState.value = ForecastState.Error(errorMessage)
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }

    private suspend fun updateGeoCodeFromWeather(currentWeather: CurrentWeather) {
        val geoCode: GeoCode = GeoCode(
            name = currentWeather.cityName,
            country = currentWeather.sys.country,
            latitude = currentWeather.coordinate.latitude,
            longitude = currentWeather.coordinate.longitude,
            timezone = currentWeather.timezone,
            zipCode = currentZipCode
        )
        currentCityName = currentWeather.cityName
        currentCoordinate = currentWeather.coordinate
        _geoCodeState.value = GeoCodeState.Success(geoCode = geoCode)
        stateGeoCodeLookup()
    }

    private suspend fun stateGeoCodeLookup() {
        val response =
            repository.requestReverseGeoCode(coordinate = (_geoCodeState.value as GeoCodeState.Success).geoCode.coordinate)
//        println("state lookup response $response")
        when (response) {
            is QueryResponseState.Success<List<GeoCode>> -> {
                val newState = response.data.first().state
                (_geoCodeState.value as GeoCodeState.Success).geoCode.state = newState
            }

            is QueryResponseState.Error -> {
                val errorMessage = "Error fetching US state: ${response.exception.message}"
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
            }
        }
    }

    private fun resetCache() {
        cachedCurrentWeather = null
        currentCityName = null
        currentZipCode = null
        currentCoordinate = null
        isCacheValid = false
        expirationTimer.reset()
    }

    private fun isCacheValid(): Boolean {
        return if (countdownTimer == null) {
            isCacheValid = true // If no timer, assume cache is valid
            true
        } else {
            isCacheValid
        }
    }

    private suspend fun coordinateFromCityName(cityName: String): Coordinate? {
        val response = repository.requestGeoCodeByCityName(cityName = cityName)
//        println("state lookup response $response")
        when (response) {
            is QueryResponseState.Success<List<GeoCode>> -> {
                return response.data.first().coordinate
            }

            is QueryResponseState.Error -> {
                val errorMessage = "Error fetching country's  state/region: ${response.exception.message}"
                _geoCodeState.value = GeoCodeState.Error(errorMessage)
                return null
            }
        }
    }
}