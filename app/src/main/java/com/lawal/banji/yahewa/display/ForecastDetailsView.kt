package com.lawal.banji.yahewa.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.SmallerPadding
import com.lawal.banji.yahewa.ui.theme.White

@Composable
fun ForecastDetailsView(forecast: Forecast) {

    val description = forecast.weather[0].description
    val windSpeed = forecast.wind.speed
    val windDirection = forecast.wind.direction
    val windGust = forecast.wind.gust
    val percentCloudiness = "${forecast.clouds.all} % cloudiness"
    val visibility = "${forecast.visibility} visibility"
    val sunrise = "${forecast.sys.sunrise} sunrise"
    val sunset = "${forecast.sys.sunset} sunset"

    val wind = "wind $windSpeed m/s ${windDirection}°"

    LazyColumn(
        modifier = Modifier
            .padding(DefaultPadding)
            .fillMaxSize()
            .background(White)
    ) {
        // Row containing City
        item {
            // City needs to be centered. Fills the width of it's center
            Text(
                text = "$sunrise $sunset",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxSize().padding(bottom = 0.dp),
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = percentCloudiness,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding))
        }
        item {
            Text(
                text = visibility,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding))
        }
        item {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding))
        }
    }
}