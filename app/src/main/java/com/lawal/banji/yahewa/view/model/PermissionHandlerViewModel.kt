package com.lawal.banji.yahewa.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lawal.banji.yahewa.request.PermissionHandler
import com.lawal.banji.yahewa.request.PermissionRequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PermissionHandlerViewModel(
    private val permissionHandler: PermissionHandler
) : ViewModel() {

    private val _permissionRequestState = MutableStateFlow<PermissionRequestState>(PermissionRequestState.Loading)
    val permissionRequestState: MutableStateFlow<PermissionRequestState> get() = _permissionRequestState

    init {  observePermissionHandlerState() }

    private  fun observePermissionHandlerState() {
        viewModelScope.launch {
            try {
                permissionHandler.permissionRequestState.collect { state ->
                    _permissionRequestState.value = state
                }
            } catch (e: Exception) {
                _permissionRequestState.value = PermissionRequestState.Error(e.message ?: "Unknown error")
            }
        }
    }
}