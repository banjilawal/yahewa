package com.lawal.banji.yahewa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.lawal.banji.yahewa.weather.view.WeatherViewModel
import com.lawal.banji.yahewa.screen.WeatherDetailsDisplay
import com.lawal.banji.yahewa.ui.theme.YahewaTheme


class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YahewaTheme {
                val weatherData by weatherViewModel.weatherData.observeAsState()
                Surface(color = MaterialTheme.colorScheme.background) {
                    if (weatherData != null) {
                        WeatherDetailsDisplay(weatherData!!)
                    } else {
                        // Show a loading indicator or placeholder
                        Text(text = "Loading...")
                    }
                }
            }
        }
        weatherViewModel.fetchWeatherData(
            latitude =  61.1667 ,
            longitude = 149.9833,
            apiKey = "43d92973340fa3166680bbe3af8d3943"
        )
    }
}
