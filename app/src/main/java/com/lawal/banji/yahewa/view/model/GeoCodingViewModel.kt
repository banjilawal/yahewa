package com.lawal.banji.yahewa.view.model
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.lawal.banji.yahewa.model.Coordinates
import com.lawal.banji.yahewa.repo.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@RequiresApi(Build.VERSION_CODES.O)
class GeoCodingViewModel(private val repository: AppRepository) : ViewModel() {

//    private val _geoCodingState = MutableStateFlow<GeoCodingState>(GeoCodingState.Loading)
//    val geoCodingState: StateFlow<GeoCodingState> get() = _geoCodingState
//
//    private val _reverseGeoCodingState = MutableStateFlow<ReverseGeoCodingState>(ReverseGeoCodingState.Loading)
//    val reverseGeoCodingState: StateFlow<ReverseGeoCodingState> get() = _reverseGeoCodingState
//
//    private var _location: MutableStateFlow<Location?> = MutableStateFlow(null)
//    val location: StateFlow<Location?> get() = _location

    private val _zipCode: MutableStateFlow<String?> = MutableStateFlow(null)
    val zipCode: StateFlow<String?> get() = _zipCode

    private val _coordinates: MutableStateFlow<Coordinates?> = MutableStateFlow(null)
    val coordinates: StateFlow<Coordinates?> get() = _coordinates

    private var previousZipCode: String? = null // Cache for ZIP code
    private var previousCoordinates: Coordinates? = null

    // Keep the init block unchanged as per your request
//    init {
//        viewModelScope.launch {
//            val zipCode = getRandomZipCode()
//            queryByZipCode(zipCode = zipCode, apiKey = AppDefault.API_KEY)
//        }
//    }
//
//    fun setLocation(newLocation: Location) {
//        _location.value= newLocation
//    }

    // Public method to set coordinates and trigger fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setCoordinates(newCoordinates: Coordinates) {
        if (previousCoordinates == newCoordinates) {
            Log.d("GeoCodingViewModel", "Coordinates $newCoordinates already queried. Skipping lookup.")
            return
        }

        previousCoordinates = newCoordinates
        _coordinates.value = newCoordinates
    }

    // Public method to set the ZIP code and trigger fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setZipCode(newZipCode: String) {
        if (previousZipCode == newZipCode) {
            Log.d("GeoCodingViewModel", "ZIP code $newZipCode already queried. Skipping lookup.")
            return
        }


        previousZipCode = newZipCode // Update cache for the ZIP code
        _zipCode.value = newZipCode
    }
//
//    // Private function to query by ZIP code without redundant recursive calls
//    @RequiresApi(Build.VERSION_CODES.O)
//    private suspend fun queryByZipCode(zipCode: String, apiKey: String) {
//        try {
//            when (val queryResult = repository.requestGeoCodingByZipCode(zipCode, apiKey)) {
//                is QueryResponseState.Success -> {
//                    _geoCodingState.value = GeoCodingState.Success(queryResult.data)
//                    val coordinates = Coordinates(
//                        longitude = queryResult.data.longitude,
//                        latitude = queryResult.data.latitude
//                    )
//                    Log.d("GeoCodingViewModel", "latitude: ${coordinates.latitude}, longitude: ${coordinates.longitude}")
//                }
//                is QueryResponseState.Error -> {
//                    _geoCodingState.value =
//                        GeoCodingState.Error("Error: ${queryResult.exception.message}")
//                }
//            }
//        } catch (e: Exception) {
//            Log.e("GeoCodingViewModel", "Unexpected error during ZIP code query: ${e.message}")
//            _geoCodingState.value = GeoCodingState.Error("Unexpected error: ${e.message}")
//        }
//    }
//
//    // Private function to query by coordinates without redundant recursive calls
//    @RequiresApi(Build.VERSION_CODES.O)
//    private suspend fun queryByCoordinates(coordinates: Coordinates, apiKey: String) {
//        try {
//            when (val queryResult = repository.requestReverseGeoCodingByCoordinates(coordinates, apiKey)) {
//                is QueryResponseState.Success -> {
//                    _reverseGeoCodingState.value = ReverseGeoCodingState.Success(queryResult.data)
//                    Log.d("ReverseGeoCodingViewModel", "ZIP code fetched: $zipCode")
//                }
//                is QueryResponseState.Error -> {
//                    _geoCodingState.value =
//                        GeoCodingState.Error("Error: ${queryResult.exception.message}")
//                }
//            }
//        } catch (e: Exception) {
//            Log.e("GeoCodingViewModel", "Unexpected error during coordinates query: ${e.message}")
//            _geoCodingState.value = GeoCodingState.Error("Unexpected error: ${e.message}")
//        }
//    }
}


