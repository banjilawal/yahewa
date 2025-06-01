package com.lawal.banji.yahewa

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.lawal.banji.yahewa.factory.GeoCodingViewModelFactory
import com.lawal.banji.yahewa.model.GeoCodeState
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.utils.getRandomCity
import com.lawal.banji.yahewa.view.model.GeoCodeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val geoCodeViewModel: GeoCodeViewModel by viewModels {
        GeoCodingViewModelFactory(AppRepository())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val randomCity = getRandomCity() // Assume this gives you a random city with coordinate
        geoCodeViewModel.loadDataByCoordinate(coordinate = getRandomCity().coordinate)
//        geoCodeViewModel.loadDataByCityName(cityName = getRandomCity().name)
//        geoCodeViewModel.loadDataByZipCode(getRandomZipCode())


        observeGeoCodeState()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun observeGeoCodeState() {
        // Observe the `geoCodeState` StateFlow directly
        lifecycleScope.launch {
            geoCodeViewModel.geoCodeState.collectLatest { state ->
                when (state) {
                    is GeoCodeState.Loading -> {
                        println("GeoCodeViewModel: Loading geocode information...")
                    }

                    is GeoCodeState.Success -> {
                        val coordinate = state.geoCode.coordinate
                        val name = state.geoCode.name
                        val geoCode = state.geoCode
                        println("GeoCodeViewModel: Geocode information received - $geoCode")
                 //       println("GeoCodeViewModel: Coordinates received - Latitude = ${coordinate.latitude}, Longitude = ${coordinate.longitude} from $name")
                    }

                    is GeoCodeState.Error -> {
                        println("GeoCodeViewModel: Error occurred - ${state.message}")
                    }
                    null -> {
                        println("GeoCodeViewModel: Null state!")
                    }
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
//import com.lawal.banji.yahewa.factory.GeoCodingViewModelFactory
//import com.lawal.banji.yahewa.navigation.ScreenRouter
//import com.lawal.banji.yahewa.navigation.Screens
//import com.lawal.banji.yahewa.repo.AppRepository
//import com.lawal.banji.yahewa.response.LocationRequestHandler
//import com.lawal.banji.yahewa.ui.theme.YahewaTheme
//import com.lawal.banji.yahewa.view.model.CurrentWeatherViewModel
//import com.lawal.banji.yahewa.view.model.ForecastViewModel
//import com.lawal.banji.yahewa.view.model.GeoCodeViewModel
//
//class MainActivity : ComponentActivity() {
//
//    private val geoCodeViewModel: GeoCodeViewModel by viewModels {
//        GeoCodingViewModelFactory(AppRepository())
//    }
//
//    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModels {
//        CurrentWeatherViewModelFactory(AppRepository(), geoCodeViewModel)
//    }
//
//    private val forecastViewModel: ForecastViewModel by viewModels {
//        ForecastViewModelFactory(AppRepository(), geoCodeViewModel)
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
//                        geoCodeViewModel = geoCodeViewModel,
//                        startDestination = Screens.Current.route // Define the starting route
//                    )
//                }
//            }
//        }
//    }
//}
