package com.lawal.banji.yahewa.request

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PermissionHandler(
    private val activity: ComponentActivity,
    private val permissions: Array<String> = DEFAULT_PERMISSIONS
) {

    companion object {
        private val DEFAULT_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    private val _permissionState = MutableStateFlow<Map<String, Boolean>>(
        permissions.associateWith { false }
    )
    val permissionState: StateFlow<Map<String, Boolean>> get() = _permissionState

    private var multiplePermissionLauncher: ActivityResultLauncher<Array<String>>? = null

    fun requestPermissions() {
        if (multiplePermissionLauncher == null) {
            // Initialize the launcher when permissions are first requested
            multiplePermissionLauncher = activity.registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissionsResult ->
                // Update the StateFlow with the latest permission results
                _permissionState.value = permissionsResult
            }
        }
        // Launch the permission request
        multiplePermissionLauncher?.launch(permissions)
    }

    fun hasPermissions(): Boolean {
        // Check if all specified permissions are already granted
        return permissions.all { permission ->
            ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
        }
    }

//    suspend fun FusedLocationProviderClient.getCurrentLocationSafely(activity: ComponentActivity): Location? {
//        // Ensure geoCode permissions are granted before fetching geoCode
//        val hasPermission = activity.requestLocationPermissions()
//        if (!hasPermission) {
//            return null // Permissions denied, return null
//        }
//    }
}


