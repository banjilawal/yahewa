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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// Permission request implementation for activity
fun ComponentActivity.registerLocationPermissionLauncher(
    onGranted: () -> Unit,
    onDenied: () -> Unit
) {
    val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        ) {
            onGranted()
        } else {
            onDenied()
        }
    }

    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    if (permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
        onGranted()
    } else {
        permissionLauncher.launch(permissions)
    }
}

// For getting location safely
suspend fun FusedLocationProviderClient.getCurrentLocationSafely(
    context: Context
): Location? {
    return if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        suspendCancellableCoroutine { continuation ->
            lastLocation.addOnSuccessListener { location ->
                continuation.resume(location)
            }.addOnFailureListener {
                continuation.resume(null)
            }
        }
    } else {
        null
    }
}

fun Context.startLocationService() {
    val serviceIntent = Intent(this, LocationService::class.java)
    ContextCompat.startForegroundService(this, serviceIntent)
}

// Service implementation
class LocationService : Service() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var serviceJob: Job? = null

    override fun onCreate() {
        super.onCreate()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForegroundService()
        startLocationUpdates()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob?.cancel() // Cancel location updates when service is destroyed
    }

    private fun startForegroundService() {
        val channelId = "LocationServiceChannel"
        val channelName = "Location Service"
        val notificationManager = getSystemService(NotificationManager::class.java)
        val notificationChannel = NotificationChannel(
            channelId, channelName, NotificationManager.IMPORTANCE_LOW
        )

        notificationManager.createNotificationChannel(notificationChannel)
        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Location Service")
            .setContentText("Tracking your location...")
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .build()
        startForeground(1, notification)
    }

    private fun startLocationUpdates() {
        serviceJob = CoroutineScope(Dispatchers.IO).launch {
            val locationFlow = createLocationFlow()
            locationFlow.collect { location ->
                if (location != null) {
                    Log.d("LocationService", "Location: Latitude=${location.latitude}, Longitude=${location.longitude}")
                } else {
                    Log.d("LocationService", "Failed to fetch location.")
                }
            }
        }
    }

    private fun createLocationFlow(): Flow<Location?> {
        return flow {
            while (true) {
                if (ContextCompat.checkSelfPermission(
                        this@LocationService,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val location = fusedLocationProviderClient.getCurrentLocationSafely(this@LocationService)
                    emit(location)
                } else {
                    emit(null)
                }
                delay(5000L) // Emit location every 5 seconds
            }
        }.flowOn(Dispatchers.IO)
    }
}
