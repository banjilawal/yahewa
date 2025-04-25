package com.lawal.banji.yahewa

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.lawal.banji.yahewa.destination.TestScreen
import com.lawal.banji.yahewa.repo.ForecastRepository
import com.lawal.banji.yahewa.ui.theme.YahewaTheme
import com.lawal.banji.yahewa.viewmodel.ForecastState
import com.lawal.banji.yahewa.viewmodel.ForecastViewModel
import com.lawal.banji.yahewa.viewmodel.WeatherViewModelFactory

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
                    when (forecastState) {
                        is ForecastState.Loading -> { CircularProgressIndicator() }
                            is  ForecastState.Success -> {
                                val forecast = (forecastState as ForecastState.Success).forecast
                                TestScreen(forecastViewModel)
                            }
                        is ForecastState.Error -> {
                            val errorMessage = (forecastState as ForecastState.Error).message
                            Text(text = errorMessage)
                        }
                    }
                }
            }
        }
    }
}
