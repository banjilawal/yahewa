package com.lawal.banji.yahewa.request

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Requests both fine and coarse geoCoding permissions dynamically.
 * @return `true` if both geoCoding permissions are granted, otherwise `false`.
 */
suspend fun ComponentActivity.requestLocationPermissions(): Boolean {
    return suspendCancellableCoroutine { continuation ->
        // Permissions to request
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        // Check if all permissions are already granted
        val allPermissionsGranted = permissions.all { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }

        if (allPermissionsGranted) {
            continuation.resume(true) // All permissions already granted
        } else {
            // Launch permission request dialog
            val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
                val granted = results.all { it.value } // Check if all requested permissions were granted
                continuation.resume(granted)
            }
            permissionLauncher.launch(permissions)
        }
    }
}

/**
 * Retrieves the user's last known geoCoding using FusedLocationProviderClient.
 * @return The last known [Location], or `null` if geoCoding retrieval fails.
 */
suspend fun FusedLocationProviderClient.getCurrentLocationSafely(activity: ComponentActivity): Location? {
    // Ensure geoCoding permissions are granted before fetching geoCoding
    val hasPermission = activity.requestLocationPermissions()
    if (!hasPermission) {
        return null // Permissions denied, return null
    }

    // Fetch the last known geoCoding
    return suspendCancellableCoroutine { continuation ->
        this.lastLocation.addOnSuccessListener { location ->
            continuation.resume(location) // Return geoCoding if available
        }.addOnFailureListener {
            continuation.resume(null) // Return null if geoCoding retrieval fails
        }
    }
}