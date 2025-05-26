package com.lawal.banji.yahewa

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.lawal.banji.yahewa.factory.CurrentWeatherViewModelFactory
import com.lawal.banji.yahewa.factory.ForecastViewModelFactory
import com.lawal.banji.yahewa.factory.WeatherViewModelFactory
import com.lawal.banji.yahewa.navigation.AppNavHost
import com.lawal.banji.yahewa.navigation.Screens
import com.lawal.banji.yahewa.repo.AppRepository
import com.lawal.banji.yahewa.response.LocationRequestHandler
import com.lawal.banji.yahewa.ui.theme.YahewaTheme
import com.lawal.banji.yahewa.view.model.AppViewModel
import com.lawal.banji.yahewa.view.model.CurrentWeatherViewModel
import com.lawal.banji.yahewa.view.model.ForecastViewModel

class MainActivity : ComponentActivity() {

    private val currentWeatherViewModel: CurrentWeatherViewModel by viewModels {
        CurrentWeatherViewModelFactory(AppRepository())
    }

    private val forecastViewModel: ForecastViewModel by viewModels {
        ForecastViewModelFactory(AppRepository())
    }

    private val appViewModel: AppViewModel by viewModels {
        WeatherViewModelFactory(AppRepository())
    }

    private lateinit var locationRequestHandler: LocationRequestHandler

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isDarkTheme = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = !isDarkTheme

        setContent {
            YahewaTheme {
                val currentWeatherState by currentWeatherViewModel.currentWeatherState.collectAsState()
                val forecastGroupState by forecastViewModel.forecastState.collectAsState()
                val forecastState by appViewModel.currentWeatherState.collectAsState()

                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController() // Create the NavController
                    AppNavHost(
                        navController = navController,       // Pass the NavController to the NavHost
                        appViewModel = appViewModel, // Pass the ViewModel for the currentWeather state
                        startDestination = Screens.Home.route // Define the starting route
                    )

                }
            }
        }
    }
}
