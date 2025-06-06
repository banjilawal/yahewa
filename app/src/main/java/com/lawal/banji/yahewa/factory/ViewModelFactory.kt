package com.lawal.banji.yahewa.factory

//import com.lawal.banji.yahewa.view.model.AppViewModel
//import com.lawal.banji.yahewa.view.model.CurrentWeatherViewModel
//import com.lawal.banji.yahewa.view.model.ForecastViewModel
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.view.model.AppViewModel

//import com.lawal.banji.yahewa.view.model.PermissionHandlerViewModel
//
//class WeatherViewModelFactory(
//    private val repository: AppRepository
//) : ViewModelProvider.Factory {
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
//            return AppViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
//
//class CurrentWeatherViewModelFactory(
//    private val repository: AppRepository,
//    private val geoCodingViewModel: AppViewModel
//) : ViewModelProvider.Factory {
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CurrentWeatherViewModel::class.java)) {
//            return CurrentWeatherViewModel(repository = repository, geoCodingViewModel = geoCodingViewModel) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
//
//class ForecastViewModelFactory(
//    private val repository: AppRepository,
//    private val geoCodingViewModel: AppViewModel
//) : ViewModelProvider.Factory {
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
//            return CurrentWeatherViewModel(repository = repository, geoCodingViewModel = geoCodingViewModel) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

class GeoCodingViewModelFactory(
    private val repository: AppRepository
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.O)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

//class PermissionHandlerViewModelFactory(
//    private val permissionHandler: PermissionHandler
//) : ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST") // To suppress unchecked cast warnings
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(PermissionHandlerViewModel::class.java)) {
//            return PermissionHandlerViewModel(permissionHandler) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

