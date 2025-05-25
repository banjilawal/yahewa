package com.lawal.banji.yahewa.factory

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.request.PermissionHandler
import com.lawal.banji.yahewa.view.model.ForecastViewModel
import com.lawal.banji.yahewa.view.model.PermissionHandlerViewModel

class WeatherViewModelFactory(
    private val repository: AppRepository
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.O)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class PermissionHandlerViewModelFactory(
    private val permissionHandler: PermissionHandler
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST") // To suppress unchecked cast warnings
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PermissionHandlerViewModel::class.java)) {
            return PermissionHandlerViewModel(permissionHandler) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

