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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.lawal.banji.yahewa.display.CurrentWeatherDisplay
import com.lawal.banji.yahewa.repo.WeatherRepository
import com.lawal.banji.yahewa.ui.theme.YahewaTheme
import com.lawal.banji.yahewa.weather.view.WeatherViewModel
import com.lawal.banji.yahewa.weather.view.WeatherViewModelFactory

class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(WeatherRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isDarkTheme = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = !isDarkTheme

        setContent {
            YahewaTheme {
                val weatherRecord by weatherViewModel.weatherRecord.observeAsState()
                val errorMessage by weatherViewModel.errorMessage.observeAsState()

                Surface(color = MaterialTheme.colorScheme.background) {
                    when {
                        weatherRecord != null -> CurrentWeatherDisplay(weatherRecord!!)
                        errorMessage != null -> Text("Error: $errorMessage")
                        else -> CircularProgressIndicator()
                    }
                }
            }
        }
    }
}



//class MainActivity : ComponentActivity() {
//
//    private val weatherViewModel: WeatherViewModel by viewModels()
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            YahewaTheme {
//                val weatherData by weatherViewModel.weatherData.observeAsState()
//                Surface(color = MaterialTheme.colorScheme.background) {
//                    if (weatherData != null) {
//                        WeatherDetailsDisplay(weatherData!!)
//                    } else {
//                        // Show a loading indicator or placeholder
//                        Text(text = "Loading...")
//                    }
//                }
//            }
//        }
//        weatherViewModel.fetchWeatherData(
//            latitude =  6.5244 ,
//            longitude = .3792,
//            apiKey = "43d92973340fa3166680bbe3af8d3943"
//        )
//    }
//}

