package com.lawal.banji.yahewa.request

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.lang.ref.WeakReference

abstract class PermissionRequest(
    activity: ComponentActivity,
    private val permissions: Array<String>
) : DefaultLifecycleObserver {

    private val _permissionRequestState = MutableStateFlow<PermissionRequestState>(PermissionRequestState.Loading)
    val permissionRequestState: StateFlow<PermissionRequestState> = _permissionRequestState

    // Weak reference to avoid memory leaks
    private val activityReference = WeakReference(activity)

    private var onGrantedCallBack: (() -> Unit)? = null
    private var onDeniedCallBack: (() -> Unit)? = null
    private var isRequesting: Boolean = false

    private var mutex: Mutex = Mutex()

    // Permission launcher
    private val permissionLauncher = activity.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        // Handle permission results
        try {
            if (results.isEmpty()) {
                // No results returned (possible error)
                onDeniedCallBack?.invoke()
            } else if (results.all { it.value }) {
                // All permissions granted
                onGrantedCallBack?.invoke()
            } else {
                // Some permissions denied
                onDeniedCallBack?.invoke()
            }
        } finally {
            // Ensure consistent cleanup
            completeRequest()
        }
    }

    init {
        if (permissions.isEmpty()) {
            throw IllegalArgumentException("Permissions array cannot be empty")
        }

        // Register lifecycle observer
        activity.lifecycle.addObserver(this)
    }

    suspend fun request(onGranted: () -> Unit, onDenied: () -> Unit) {
        mutex.withLock {
            if (isRequesting) {
                // A request is already in progress; return to avoid race conditions
                return
            }
            isRequesting = true

            try {
                onGrantedCallBack = { onGranted() }
                onDeniedCallBack = { onDenied() }

                val activity = activityReference.get()
                    ?: throw IllegalStateException("Activity reference is null")

                if (hasPermissions(activity)) {
                    // Permissions already granted
                    onGrantedCallBack?.invoke()
                    completeRequest() // Cleanup immediately
                } else {
                    // Request permissions
                    permissionLauncher.launch(permissions)
                }
            } catch (e: Exception) {
                completeRequest() // Cleanup when exceptions occur
                throw e
            }
        }
    }

    private fun completeRequest() {
        isRequesting = false
        cleanUpCallbacks()
    }

    abstract fun hasPermissions(context: Context): Boolean

    private fun cleanUpCallbacks() {
        onGrantedCallBack = null
        onDeniedCallBack = null
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        // Cleanup lifecycle observer and resources
        cleanUpCallbacks()
        activityReference.clear()
        owner.lifecycle.removeObserver(this)
    }
}

class LocationPermissionRequest(
    activity: ComponentActivity
) : PermissionRequest(
    activity,
    arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
) {
    override fun hasPermissions(context: Context): Boolean {
        // Only require fine location permission for the permissions to be considered granted
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
}

sealed class PermissionRequestState {
    object Loading: PermissionRequestState()
    object Granted: PermissionRequestState()
    object Denied: PermissionRequestState()
    data class Error(val message: String): PermissionRequestState()
}


