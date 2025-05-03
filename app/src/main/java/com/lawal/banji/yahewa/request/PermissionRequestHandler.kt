package com.lawal.banji.yahewa.request

import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
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