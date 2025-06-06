//package com.lawal.banji.yahewa.view.model
//
//import android.os.Build
//import androidx.annotation.RequiresApi
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.lawal.banji.yahewa.model.Coordinate
//import com.lawal.banji.yahewa.model.CurrentWeatherState
//import com.lawal.banji.yahewa.repo.AppRepository
//import com.lawal.banji.yahewa.repo.QueryResponseState
//import com.lawal.banji.yahewa.utils.AppDefault
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//@RequiresApi(Build.VERSION_CODES.O)
//class CurrentWeatherViewModel(
//    private val repository: AppRepository,
//    private val geoCodingViewModel: AppViewModel
//) : ViewModel() {
//
//    private val _currentWeatherState = MutableStateFlow<CurrentWeatherState>(CurrentWeatherState.Loading)
//    val currentWeatherState: StateFlow<CurrentWeatherState> get() = _currentWeatherState
//
//    private var _coordinates: MutableStateFlow<Coordinate?> = MutableStateFlow(null)
//    val coordinate: StateFlow<Coordinate?> get() = _coordinates
//
//    private var _zipCode: MutableStateFlow<String?> = MutableStateFlow(null)
//    val zipCode: StateFlow<String?> get() = _zipCode
//
//    private var _location: MutableStateFlow<Location?> = MutableStateFlow(null)
//    val location: StateFlow<Location?> get() = _location
//
//    private var previousCoordinates: Coordinate? = null // Cache for coordinate
//    private var previousZipCode: String? = null // Cache for ZIP code
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
//
//
//    init {
//        viewModelScope.launch {
//            val city = getRandomCity()
//            val coordinate = city.coordinate
//            System.out.println("CurrentViewModel is getting current weather conditions for  $city with  repository.requestCurrentWeatherByCoordinates")
//            queryByCoordinates(coordinate = coordinate, apiKey = AppDefault.API_KEY)
//        }
//    }
//
//    fun setLocation(newLocation: Location) {
//        _location.value= newLocation
//    }
//
//    // Public method to set the zip code so the ViewModel can handle fetching
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun setZipcode(newZipCode: String) {
////        if (previousZipCode == newZipCode) {
////            println("ZIP code $newZipCode already queried. Skipping lookup.")
////            return
////        }
//
//        _zipCode.value = newZipCode
//        previousZipCode = newZipCode // Update cache for the ZIP code
//        System.out.println("CurrentViewModel is going to call repository.requestCurrentWeatherByZipCode with zipcode $newZipCode")
//        queryByZipCode(newZipCode, AppDefault.API_KEY)
//    }
//
//    // Public method to set the zip code so the ViewModel can handle fetching
//    @RequiresApi(Build.VERSION_CODES.O)
//    fun setCoordinates(newCoordinates: Coordinate) {
////        if (previousCoordinates == newCoordinates) {
////            println("ZIP code $coordinate  already queried. Skipping lookup.")
////            return
////        }
//
//        _coordinates.value = newCoordinates
//        previousCoordinates = newCoordinates // Update cache for the ZIP code
//        System.out.println("CurrentViewModel is going to call repository.requestCurrentWeatherByCoordinates with new coordinate $newCoordinates")
//        queryByCoordinates(coordinate = newCoordinates, apiKey= AppDefault.API_KEY)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun queryByZipCode(zipCode: String, apiKey: String) {
//        viewModelScope.launch {
//            when (val queryResult = repository.requestCurrentWeatherByZipCode(zipCode, apiKey)) {
//                is QueryResponseState.Success -> {
//                    _currentWeatherState.value = CurrentWeatherState.Success(queryResult.data)
//                    val latitude = queryResult.data.coordinate.latitude
//                    val longitude = queryResult.data.coordinate.longitude
//                    System.out.println("fetched for Zipcode:$zipCode latitude:$latitude longitude:$longitude getting forecastRecords now")
//                }
//
//                is QueryResponseState.Error -> {
//                    _currentWeatherState.value =
//                        CurrentWeatherState.Error(queryResult.exception.message ?: "Unknown Error")
//                }
//            }
//        }
//    }
//
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    private fun queryByCoordinates(coordinate: Coordinate, apiKey: String) {
//        if (previousCoordinates == coordinate) {
//            println("Coordinate $coordinate.latitude, $coordinate.longitude already queried. Skipping lookup.")
//            return
//        }
//
//       setCoordinates(newCoordinates = coordinate)// Update the coordinate cache
//        viewModelScope.launch {
//            System.out.println("latitude:${coordinate.latitude} longitude:${coordinate.longitude} getting currentWeather now")
//            when (val queryResult = repository.requestCurrentWeatherByCoordinates(coordinate = coordinate, apiKey = apiKey)) {
//                is QueryResponseState.Success -> {
//                    _currentWeatherState.value = CurrentWeatherState.Success(queryResult.data)
//                    val city = queryResult.data.city
//                    val coordinates = queryResult.data.coordinate
//                    System.out.println("latitude:${coordinates.latitude} longitude:${coordinates.longitude} getting forecastRecords now")
//                } is QueryResponseState.Error -> {
//                    _currentWeatherState.value = CurrentWeatherState.Error("Error: ${queryResult.exception.message}")
//                }
//            }
//        }
//    }
//}