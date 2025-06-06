package com.lawal.banji.yahewa.request

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


// PermissionGroup

class Request (
    private val activity: Activity,
    private val permissions: List<String>
) {
    fun getGrantedPermissions(): List<String> {
        return permissions.filter { permission ->
            ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun getDeniedPermissions(): List<String> {
        return permissions.filter { permission ->
            ActivityCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED
        }
    }

    fun requestAllPermissions() {
        ActivityCompat.requestPermissions(activity, permissions.toTypedArray(), 1)
    }

    fun revokePermission(permissionID: Int) {}
    fun requestPermission(permission: String) {}
}
//
//sealed class RequestState {
//    data class Granted(val request: Request): RequestState()
//    data class Denied(val request: Request): RequestState()
//    data class Error(val message: String): RequestState()
//}
//
//class PermissionHelper(private val activity: Activity) {
//
//    // Request permissions
//    fun requestPermissions(
//        permissions: List<String>,
//        requestCode: Int
//    ) {
//        ActivityCompat.requestPermissions(
//            activity,
//            permissions.toTypedArray(),
//            requestCode
//        )
//    }
//
//    // Check if all permissions are granted
//    fun arePermissionsGranted(permissions: List<String>): Boolean {
//        return permissions.all { permission ->
//            ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
//        }
//    }
//}
