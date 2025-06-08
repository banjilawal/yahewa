package com.lawal.banji.yahewa

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.lawal.banji.yahewa.factory.AppViewModelFactory
import com.lawal.banji.yahewa.model.GeoCodeState
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.request.PermissionHandler
import com.lawal.banji.yahewa.utils.getRandomCity
import com.lawal.banji.yahewa.utils.getRandomZipCode
import com.lawal.banji.yahewa.view.model.AppViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    private var permissionHandler : PermissionHandler = PermissionHandler(this)
    private val appViewModel: AppViewModel by viewModels {
        AppViewModelFactory(AppRepository())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val randomCity = getRandomCity() // Assume this gives you a random city with coordinate
//        appViewModel.loadDataByCoordinate(coordinate = getRandomCity().coordinate)
//        appViewModel.loadDataByCityName(cityName = getRandomCity().name)
//        appViewModel.loadDataByZipCode(getRandomZipCode())
        setupPermissionHandling()
        sendRandomQuery()
        observeGeoCodeState()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendRandomQuery() {
        when (Random.nextInt(3)) { // Randomly generates 0, 1, or 2
            0 -> {
                val city = getRandomCity()
                println("Loading data by coordinate: ${city.coordinate}")
                repeat(1) { appViewModel.loadDataByCoordinate(coordinate = city.coordinate) }
            }

            1 -> {
                val city = getRandomCity()
//                println("Loading data by city name: ${city.name}")
                repeat(1) { appViewModel.loadDataByCityName(cityName = city.name) }
            }

            2 -> {
                val randomZipCode = getRandomZipCode()
//                println("Loading data by zip code: $randomZipCode")
                repeat(1) { appViewModel.loadDataByZipCode(zipCode = randomZipCode) }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeGeoCodeState() {
        // Observe the `geoCodeState` StateFlow directly
        lifecycleScope.launch {
            appViewModel.geoCodeState.collectLatest { state ->
                when (state) {
                    is GeoCodeState.Loading -> {
//                        println("AppViewModel: Loading geocode information...")
                    }

                    is GeoCodeState.Success -> {
                        val coordinate = state.geoCode.coordinate
                        val name = state.geoCode.name
                        val geoCode = state.geoCode
//                        println("AppViewModel: Geocode information received - $geoCode")
                        //       println("AppViewModel: Coordinates received - Latitude = ${coordinate.latitude}, Longitude = ${coordinate.longitude} from $name")
                    }

                    is GeoCodeState.Error -> {
                        println("AppViewModel: Error occurred - ${state.message}")
                    }

                    null -> {
                        println("AppViewModel: Null state!")
                    }
                }
            }
        }
    }

    private fun setupPermissionHandling() {
        permissionHandler = PermissionHandler(this)
        observePermissionState()

        if  (permissionHandler.hasPermissions()) {
            println("Permissions are already granted!")
        } else {
            println("Requesting permissions...")
            permissionHandler.requestPermissions()
        }

//        findViewById<Button>(R.id.requestPermissionsButton).setOnClickListener {
//            if (!permissionHandler.hasPermissions()) {
//                permissionHandler.requestPermissions()
//            } else {
//                println("Permissions are already granted!")
//            }
//        }
    }

    private fun observePermissionState() {
        lifecycleScope.launchWhenStarted {
            permissionHandler.permissionState.collect { state ->
                state.forEach { (permission, isGranted) ->
                    println("Permission: $permission, Granted: $isGranted")
                }
            }
        }
    }


}


//import android.content.res.Configuration
//import android.os.Build
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.viewModels
//import androidx.annotation.RequiresApi
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.core.view.WindowCompat
//import androidx.core.view.WindowInsetsControllerCompat
//import androidx.navigation.compose.rememberNavController
//import com.lawal.banji.yahewa.factory.CurrentWeatherViewModelFactory
//import com.lawal.banji.yahewa.factory.ForecastViewModelFactory
//import com.lawal.banji.yahewa.factory.AppViewModelFactory
//import com.lawal.banji.yahewa.navigation.ScreenRouter
//import com.lawal.banji.yahewa.navigation.Screens
//import com.lawal.banji.yahewa.repo.AppRepository
//import com.lawal.banji.yahewa.response.LocationRequestHandler
//import com.lawal.banji.yahewa.ui.theme.YahewaTheme
//import com.lawal.banji.yahewa.view.model.CurrentWeatherViewModel
//import com.lawal.banji.yahewa.view.model.ForecastViewModel
//import com.lawal.banji.yahewa.view.model.AppViewModel
//
//class MainActivity : ComponentActivity() {
//
//    private val appViewModel: AppViewModel by viewModels {
//        AppViewModelFactory(AppRepository())
//    }
//
//    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModels {
//        CurrentWeatherViewModelFactory(AppRepository(), appViewModel)
//    }
//
//    private val forecastViewModel: ForecastViewModel by viewModels {
//        ForecastViewModelFactory(AppRepository(), appViewModel)
//    }
//
////    private val appViewModel: AppViewModel by viewModels {
////        WeatherViewModelFactory(AppRepository())
////    }
//
//    private lateinit var locationRequestHandler: LocationRequestHandler
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val isDarkTheme = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
//        WindowCompat.setDecorFitsSystemWindows(window, true)
//        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = !isDarkTheme
//
//        setContent {
//            YahewaTheme {
//                val currentWeatherState by currentWeatherViewModel.currentWeatherState.collectAsState()
//                val forecastState by forecastViewModel.forecastState.collectAsState()
////                val forecastState by appViewModel.currentWeatherState.collectAsState()
//
//                Surface(color = MaterialTheme.colorScheme.background) {
//                    val navController = rememberNavController() // Create the NavController
//                    ScreenRouter(
//                        navController = navController,       // Pass the NavController to the NavHost
//                        currentWeatherViewModel = currentWeatherViewModel,
//                        forecastViewModel = forecastViewModel,
//                        appViewModel = appViewModel,
//                        startDestination = Screens.Current.route // Define the starting route
//                    )
//                }
//            }
//        }
//    }
//}
