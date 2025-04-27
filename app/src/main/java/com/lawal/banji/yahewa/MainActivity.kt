package com.lawal.banji.yahewa

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.lawal.banji.yahewa.navigation.AppNavHost
import com.lawal.banji.yahewa.navigation.Screens
import com.lawal.banji.yahewa.repo.ForecastRepository
import com.lawal.banji.yahewa.ui.theme.YahewaTheme
import com.lawal.banji.yahewa.view.model.ForecastViewModel
import com.lawal.banji.yahewa.view.model.WeatherViewModelFactory

class MainActivity : ComponentActivity() {

    private val forecastViewModel: ForecastViewModel by viewModels {
        WeatherViewModelFactory(ForecastRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isDarkTheme = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = !isDarkTheme

        setContent {
            YahewaTheme {
                val forecastState by forecastViewModel.forecastState.collectAsState()

                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController() // Create the NavController
                    AppNavHost(
                        navController = navController,       // Pass the NavController to the NavHost
                        forecastViewModel = forecastViewModel, // Pass the ViewModel for the forecast state
                        startDestination = Screens.Home.route // Define the starting route
                    )

                }
            }
        }
    }
}
