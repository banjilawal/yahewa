package com.lawal.banji.yahewa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.lawal.banji.yahewa.ui.theme.YahewaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YahewaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                   val weatherObservation = WeatherObservation(
                        location = "New York",
                        currentTemperature = Temperature(75),
                        feelsLikeTemperature = Temperature(77),
                        weatherCondition = "Sunny",
                        lowTemperature = Temperature(70),
                        highTemperature = Temperature(80),
                        percentHumidity = 60,
                        pressure = 1012,
                        icon = ContextCompat.getDrawable(this, R.drawable.sunny) // Replace with actual drawable resource
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