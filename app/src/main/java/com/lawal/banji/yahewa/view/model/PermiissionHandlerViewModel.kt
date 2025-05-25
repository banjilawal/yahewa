package com.lawal.banji.yahewa.view.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.lawal.banji.yahewa.request.PermissionHandler
import com.lawal.banji.yahewa.request.PermissionRequestState
import kotlinx.coroutines.flow.MutableStateFlow

class PermiissionHandlerViewModel(
    application: Application,
    private val permissionHandler: PermissionHandler
) : AndroidViewModel(application) {

    val _permissionRequestState = MutableStateFlow<PermissionRequestState>(PermissionRequestState.Loading)
//    val permissionRequestState: MutableStateFlow<PermissionRequestState> get() = _permissionRequestState


}