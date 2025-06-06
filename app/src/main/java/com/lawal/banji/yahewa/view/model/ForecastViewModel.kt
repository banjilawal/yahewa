package com.lawal.banji.yahewa.view.model
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.lawal.banji.yahewa.model.Coordinate
//import com.lawal.banji.yahewa.model.ForecastState
//import com.lawal.banji.yahewa.repo.AppRepository
//import com.lawal.banji.yahewa.repo.QueryResponseState
//import com.lawal.banji.yahewa.utils.AppDefault
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//@RequiresApi(Build.VERSION_CODES.O)
//class ForecastViewModel(
//    private val repository: AppRepository,
//    private val geoCodingViewModel: AppViewModel
//) : ViewModel() {
//
//    private val _forecastState =
//        MutableStateFlow<ForecastState>(ForecastState.Loading)
//    val forecastState: StateFlow<ForecastState> get() = _forecastState

//    private var _coordinatesState: MutableStateFlow<Coordinate?> = MutableStateFlow(null)
//    val coordinate: StateFlow<Coordinate?> get() = _coordinatesState
//
//    private var _zipCodeState: MutableStateFlow<String?> = MutableStateFlow(null)
//    val zipCode: StateFlow<String?> get() = _zipCodeState
//
//    private var _geoCodingState: MutableStateFlow<GeoCode?> = MutableStateFlow(null)
//    val goeCodingState:  StateFlow<GeoCode?> get() = _geoCodingState
//
//    private var _locationState: MutableStateFlow<Location?> = MutableStateFlow(null)
//    val location: StateFlow<Location?> get() = _locationState
//
//    private val _cityLookupState = MutableStateFlow<CityState>(CityState.Loading)
//    val cityState: StateFlow<CityState> get() = _cityLookupState

//    private var previousCoordinates: Coordinate? = null // Cache for coordinate
//    private var previousZipCode: String? = null // Cache for ZIP code
//
//    init {
//        viewModelScope.launch {
//            val city = getRandomCity()
//            val coordinate = city.coordinate
//            queryByCoordinates(coordinate = coordinate , apiKey = AppDefault.API_KEY)
//        }
//    }
//
//    fun updateLocation(location: Location) {
//        _locationState.value= location
//    }
//
//    init {
//        // Observe changes in ZIP code
//        viewModelScope.launch {
//            geoCodingViewModel.zipCode.collect { zipCode ->
//                zipCode?.let {
//                    queryByZipCode(it, AppDefault.API_KEY)
//                }
//            }
//        }
//
//        // Observe changes in coordinate
//        viewModelScope.launch {
//            geoCodingViewModel.coordinate.collect { coordinates ->
//                coordinates?.let {
//                    queryByCoordinates(it, AppDefault.API_KEY)
//                }
//            }
//        }
//    }


    // Public method to set the zip code so the ViewModel can handle fetching
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun setZipcode(newZipCode: String) {
//        if (previousZipCode == newZipCode) {
//            println("ZIP code $newZipCode already queried. Skipping lookup.")
//            return
//        }
//
//        _zipCodeState.value = newZipCode
//        previousZipCode = newZipCode // Update cache for the ZIP code
//        System.out.println("ForecastViewModel .setCoordinates has updated the coordinate to $newZipCode")
//        forecastByZipCodeHelper(newZipCode, AppDefault.API_KEY)
//    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun setCoordinates(newCoordinates: Coordinate) {
////        if (previousCoordinates == newCoordinates) {
////            println("$newCoordinates  already queried. Skipping lookup.")
////            return
////        }
//
//        _coordinatesState.value = newCoordinates
//        previousCoordinates = newCoordinates // Update cache for the ZIP code
//        queryByCoordinates(coordinate = newCoordinates, apiKey= AppDefault.API_KEY)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun queryByZipCode(zipCode: String, apiKey: String) {
////        if (previousZipCode == zipCode) {
////            println("ZipCode $zipCode already  queried. Skipping lookup.")
////            return
////        }
////        setZipcode(newZipCode = zipCode)
//        System.out.println("ForecastViewModel is going to call repository.requestForecastByZipCode with zipcode $zipCode")
//        forecastByZipCodeHelper(zipCode, apiKey)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun forecastByZipCodeHelper(zipCode: String, apiKey: String) {
//        System.out.println("ZipCodeHelper is  calling repository.requestGeoCodingByZipCode with  $zipCode")
//        viewModelScope.launch {
//            when (val queryResult = repository.requestGeoCodingByZipCode(zipCode, apiKey = apiKey)) {
//                is QueryResponseState.Success -> {
////                    _geoCodingState.value = queryResult.data
////                    System.out.println("ZipCodeHelper received geoCode data  $_geoCodingState.value?")
//                    val latitude = queryResult.data.latitude
//                    val longitude = queryResult.data.longitude
////                    System.out.println("ForecastViewModel received longitude $longitude and latitude $latitude for zip code  $zipCode")
////                    setCoordinates(Coordinate(latitude = latitude, longitude = longitude))
//                } is QueryResponseState.Error -> {
//                _forecastState.value =
//                    ForecastState.Error("Failed to fetch currentWeather data: ${queryResult.exception.message}")
//            }
//            }
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun queryByCoordinates(coordinate: Coordinate, apiKey: String) {
//        System.out.println("ForecastViewModel is getting the forecast  for coordinate  $coordinate")
//        viewModelScope.launch {
//            when (val queryResult = repository.requestForecastByCoordinates(
//                coordinate = coordinate,
//                numberOfRecords = AppDefault.NUMBER_OF_FORECASTS,
//                apiKey = apiKey
//            )
//            ) {
//                is QueryResponseState.Success -> {
//                    _forecastState.value = ForecastState.Success(queryResult.data)
//                    val cityName = queryResult.data.city.name
//                    val country = queryResult.data.city.country
//                    val sunset  = queryResult.data.forecastRecords[0].sunset
//                    val maxTemperature = queryResult.data.forecastRecords[0].temperature.max
//                    System.out.println("ForecastViewModel  successfully   got  $cityName forecastRecords for country $country with sunset at $sunset and max temperature of $maxTemperature")
//                } is QueryResponseState.Error -> {
//                    _forecastState.value =
//                        ForecastState.Error("Failed to fetch forecast data: ${queryResult.exception.message}")
//                }
//            }
//        }
//    }
//}

