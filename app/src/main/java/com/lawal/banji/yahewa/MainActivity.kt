package com.lawal.banji.yahewa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.lawal.banji.yahewa.ui.theme.YahewaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YahewaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                   val weatherObservation = WeatherObservation(
                       latitude = 40.7128,
                       longitude = -74.0060,
                       location = "New York",
                       currentTemperature = 75.0,
                       feelsLikeTemperature = 77.0,
                       description = "Sunny",
                       lowTemperature = 70.0,
                       highTemperature = 80.0,
                       percentHumidity = 60.0,
                       pressure = 1012,
                       icon = ContextCompat.getDrawable(this, R.drawable.sunny)
                    )
                    WeatherObservationDisplay(weatherObservation)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    YahewaTheme {
        Greeting("Welcome to Yahewa: The Weather App")
    }
}