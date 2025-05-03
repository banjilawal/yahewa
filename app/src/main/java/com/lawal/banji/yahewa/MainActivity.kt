package com.lawal.banji.yahewa

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.LocationServices
import com.lawal.banji.yahewa.request.getCurrentLocationSafely
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var locationPermissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Register for activity result
        locationPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[android.Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                permissions[android.Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                fetchCurrentLocation() // Proceed to fetch location if permissions are granted
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestLocationPermissions() {
        locationPermissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }

    private fun fetchCurrentLocation() {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this)

        lifecycleScope.launch { // Use the lifecycleScope to safely perform coroutine work
            val location = fusedLocationProviderClient.getCurrentLocationSafely(this@MainActivity)
            if (location != null) {
                println("Current Location - Latitude: ${location.latitude}, Longitude: ${location.longitude}")
            } else {
                println("Unable to fetch current location")
            }
        }
    }
}


