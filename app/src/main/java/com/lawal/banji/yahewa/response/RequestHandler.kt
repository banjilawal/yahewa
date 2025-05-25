package com.lawal.banji.yahewa.response

import android.location.Location
import androidx.activity.ComponentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lawal.banji.yahewa.request.LocationPermissionRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationRequestHandler(
    private val activity: ComponentActivity,
    private val callback: LocationCallback
) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)

    private val locationPermissionRequest = LocationPermissionRequest(activity)

    /**
     * Public API to handle location fetching.
     */
    fun getCurrentLocation() {
        CoroutineScope(Dispatchers.Main).launch {
            locationPermissionRequest.request(
                onGranted = { fetchLocationInBackground() },
                onDenied = { callback.onPermissionDenied() }
            )
        }
    }

    /**
     * Fetches the user's location in the background after permissions are granted.
     */
    private fun fetchLocationInBackground() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Check permissions before accessing location data
                if (locationPermissionRequest.hasPermissions(activity)) {
                    // Fetch the last known location
                    val location: Location? = fusedLocationClient.lastLocation.result
                    CoroutineScope(Dispatchers.Main).launch {
                        if (location != null) {
                            callback.onLocationReceived(location)
                        } else {
                            callback.onLocationUnavailable()
                        }
                    }
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        callback.onPermissionDenied() // Handle missing permissions
                    }
                }
            } catch (e: SecurityException) {
                e.printStackTrace()
                CoroutineScope(Dispatchers.Main).launch {
                    callback.onPermissionDenied() // Handle security exceptions
                }
            } catch (e: Exception) {
                e.printStackTrace()
                CoroutineScope(Dispatchers.Main).launch {
                    callback.onLocationError(e) // Inform about an error
                }
            }
        }
    }

    /**
     * Callback interface to handle location results.
     */
    interface LocationCallback {
        fun onLocationReceived(location: Location)
        fun onLocationUnavailable()
        fun onPermissionDenied()
        fun onLocationError(e: Exception)
    }
}
