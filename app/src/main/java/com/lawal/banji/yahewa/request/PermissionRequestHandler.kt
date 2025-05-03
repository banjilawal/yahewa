package com.lawal.banji.yahewa.request

import android.Manifest
import android.location.Location
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


suspend fun ComponentActivity.requestLocationPermission(): Boolean {
    return suspendCancellableCoroutine { continuation ->
        val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            continuation.resume(isGranted)
        }
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}

suspend fun FusedLocationProviderClient.getCurrentLocation(): Location? {
    return suspendCancellableCoroutine { continuation ->
        this.lastLocation.addOnSuccessListener { location ->
            continuation.resume(location)
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}
