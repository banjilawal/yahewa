package com.lawal.banji.yahewa.request

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

// Function to request both "fine" and "coarse" location permissions dynamically
suspend fun ComponentActivity.requestLocationPermissions(): Boolean {
    return suspendCancellableCoroutine { continuation ->
        val permissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        val allPermissionsGranted = permissions.all { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }

        if (allPermissionsGranted) {
            continuation.resume(true)
        } else {
            val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { results ->
                val granted = results.all { it.value }
                continuation.resume(granted)
            }
            permissionLauncher.launch(permissions)
        }
    }
}

// Function to safely retrieve the current location
suspend fun FusedLocationProviderClient.getCurrentLocationSafely(activity: ComponentActivity): Location? {
    val hasPermission = activity.requestLocationPermissions()
    if (!hasPermission) {
        return null
    }

    return suspendCancellableCoroutine { continuation ->
        this.lastLocation.addOnSuccessListener { location ->
            continuation.resume(location)
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}

// Function to start the Foreground Service for continuous location updates
fun Context.startLocationService() {
    val serviceIntent = Intent(this, LocationService::class.java)
    ContextCompat.startForegroundService(this, serviceIntent)
}

// Foreground service for continuous location updates
class LocationService : Service() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate() {
        super.onCreate()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundService()
        fetchLocationUpdates()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun startForegroundService() {
        val channelId = "LocationServiceChannel"
        val channelName = "Location Service"
        val notificationManager = getSystemService(NotificationManager::class.java)
        val notificationChannel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)

        notificationManager.createNotificationChannel(notificationChannel)
        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Location Service")
            .setContentText("Tracking your location...")
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .build()
        startForeground(1, notification)
    }

    private fun fetchLocationUpdates() {
        CoroutineScope(Dispatchers.IO).launch {
            while (true) { // Continuously fetch location updates
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        Log.d("LocationService", "Location: Latitude=${location.latitude}, Longitude=${location.longitude}")
                    } else {
                        Log.d("LocationService", "Failed to fetch location.")
                    }
                }
                delay(5000L) // Fetch every 5 seconds
            }
        }
    }
}
