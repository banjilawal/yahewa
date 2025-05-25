package com.lawal.banji.yahewa.response

import android.Manifest
import androidx.annotation.RequiresPermission

import android.location.Location
import androidx.activity.ComponentActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.lawal.banji.yahewa.request.LocationPermissionHandler

class LocationRequestHandler(
    private val activity: ComponentActivity,
    private val callback: LocationCallback
) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)

    private val locationPermissionRequest = LocationPermissionHandler(activity)

    /**
     * Public method to fetch the current location.
     */
    suspend fun getCurrentLocation() {
        // Request permissions using LocationPermissionHandler
        locationPermissionRequest.request(
            onGranted = @androidx.annotation.RequiresPermission(allOf = [android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION]) { fetchLastLocation() },
            onDenied = { callback.onPermissionDenied() }
        )
    }

    /**
     * Fetch the last known location.
     */
    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun fetchLastLocation() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    // Successfully retrieved location
                    callback.onLocationReceived(location)
                } else {
                    // No location available
                    callback.onLocationUnavailable()
                }
            }
            .addOnFailureListener { exception ->
                // Handle location retrieval errors
                callback.onLocationError(exception)
            }
    }

    /**
     * Callback interface to handle location results.
     */
    interface LocationCallback {
        fun onLocationReceived(location: Location) // Location fetched successfully
        fun onLocationUnavailable() // Location is unavailable
        fun onPermissionDenied() // Permissions were denied
        fun onLocationError(e: Exception) // Error occurred during location retrieval
    }
}