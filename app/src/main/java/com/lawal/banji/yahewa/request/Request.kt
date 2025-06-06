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
