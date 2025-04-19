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
        // Let the ViewModel handle initialization
        weatherViewModel.initializeWeatherData()
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

//  san diego lat:32.715736, long:-117.161087
//  lagos lat:6.5244, long:3.3792
//  new york lat:40.7128, long:-74.0060
//  london lat:51.5074, long:-0.1278
// tucson lat:32.22174, long:--110.92648
//  chicago lat:41.8781, long:-87.6298
// minneapolis lat:44.9778, long:-93.2650
// anchorage lat:61.2176, long:-149.8631