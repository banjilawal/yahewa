package com.lawal.banji.yahewa.view.model
import android.location.Location
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.model.Coordinates
import com.lawal.banji.yahewa.model.GeoCodingState
import com.lawal.banji.yahewa.model.ReverseGeoCodingState
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.repo.QueryResponseState
import com.lawal.banji.yahewa.utils.AppDefault
import com.lawal.banji.yahewa.utils.getRandomZipCode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class GeoCodingViewModel(private val repository: AppRepository) : ViewModel() {

    private val _geoCodingState = MutableStateFlow<GeoCodingState>(GeoCodingState.Loading)
    val geoCodingState: StateFlow<GeoCodingState> get() = _geoCodingState

    private val _reverseGeoCodingState = MutableStateFlow<ReverseGeoCodingState>(ReverseGeoCodingState.Loading)
    val reverseGeoCodingState: StateFlow<ReverseGeoCodingState> get() = _reverseGeoCodingState

    private var _location: MutableStateFlow<Location?> = MutableStateFlow(null)
    val location: StateFlow<Location?> get() = _location

    private var _zipCode: MutableStateFlow<String?> = MutableStateFlow(null)
    val zipCode: StateFlow<String?> get() = _zipCode

    private var _coordinates: MutableStateFlow<Coordinates?> = MutableStateFlow(null)
    val coordinates: StateFlow<Coordinates?> get() = _coordinates

    private var previousZipCode: String? = null // Cache for ZIP code
    private var previousCoordinates: Coordinates? = null

    // Keep the init block unchanged as per your request
    init {
        viewModelScope.launch {
            val zipCode = getRandomZipCode()
            queryByZipCode(zipCode = zipCode, apiKey = AppDefault.API_KEY)
        }
    }

    fun setLocation(newLocation: Location) {
        _location.value= newLocation
    }

    // Public method to set coordinates and trigger fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setCoordinates(newCoordinates: Coordinates) {
        if (previousCoordinates == newCoordinates) {
            Log.d("GeoCodingViewModel", "Coordinates $newCoordinates already queried. Skipping lookup.")
            return
        }

        _coordinates.value = newCoordinates
        previousCoordinates = newCoordinates
        viewModelScope.launch {
            queryByCoordinates(newCoordinates, AppDefault.API_KEY)
        }
    }

    // Public method to set the ZIP code and trigger fetching
    @RequiresApi(Build.VERSION_CODES.O)
    fun setZipCode(newZipCode: String) {
        if (previousZipCode == newZipCode) {
            Log.d("GeoCodingViewModel", "ZIP code $newZipCode already queried. Skipping lookup.")
            return
        }

        _zipCode.value = newZipCode
        previousZipCode = newZipCode // Update cache for the ZIP code
        viewModelScope.launch {
            queryByZipCode(newZipCode, AppDefault.API_KEY)
        }
    }

    // Private function to query by ZIP code without redundant recursive calls
    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun queryByZipCode(zipCode: String, apiKey: String) {
        try {
            when (val queryResult = repository.requestGeoCodingByZipCode(zipCode, apiKey)) {
                is QueryResponseState.Success -> {
                    _geoCodingState.value = GeoCodingState.Success(queryResult.data)
                    val coordinates = Coordinates(
                        longitude = queryResult.data.longitude,
                        latitude = queryResult.data.latitude
                    )
                    Log.d("GeoCodingViewModel", "latitude: ${coordinates.latitude}, longitude: ${coordinates.longitude}")
                }
                is QueryResponseState.Error -> {
                    _geoCodingState.value =
                        GeoCodingState.Error("Error: ${queryResult.exception.message}")
                }
            }
        } catch (e: Exception) {
            Log.e("GeoCodingViewModel", "Unexpected error during ZIP code query: ${e.message}")
            _geoCodingState.value = GeoCodingState.Error("Unexpected error: ${e.message}")
        }
    }

    // Private function to query by coordinates without redundant recursive calls
    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun queryByCoordinates(coordinates: Coordinates, apiKey: String) {
        try {
            when (val queryResult = repository.requestReverseGeoCodingByCoordinates(coordinates, apiKey)) {
                is QueryResponseState.Success -> {
                    _reverseGeoCodingState.value = ReverseGeoCodingState.Success(queryResult.data)
                    Log.d("ReverseGeoCodingViewModel", "ZIP code fetched: $zipCode")
                }
                is QueryResponseState.Error -> {
                    _geoCodingState.value =
                        GeoCodingState.Error("Error: ${queryResult.exception.message}")
                }
            }
        } catch (e: Exception) {
            Log.e("GeoCodingViewModel", "Unexpected error during coordinates query: ${e.message}")
            _geoCodingState.value = GeoCodingState.Error("Unexpected error: ${e.message}")
        }
    }
}


