package com.lawal.banji.yahewa.display

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.model.CurrentConditions
import com.lawal.banji.yahewa.ui.theme.DefaultBoxColor
import com.lawal.banji.yahewa.ui.theme.DefaultHeadingColor
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.LargeIconSize
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray
import com.lawal.banji.yahewa.ui.theme.SandLighter
import com.lawal.banji.yahewa.ui.theme.SmallPadding
import com.lawal.banji.yahewa.ui.theme.SmallerPadding
import com.lawal.banji.yahewa.ui.theme.White
import com.lawal.banji.yahewa.utils.WeatherIcon


@Composable
fun ForecastView(currentConditions: CurrentConditions) {
    val city = currentConditions.city
    val state = currentConditions.state
    val country = currentConditions.sys.country
    val currentTemperature = "${currentConditions.main.temperature}°"
    val temperatureFeelsLike = "Feels like ${currentConditions.main.temperatureFeelsLike}°"
    val lowTemperature = "Low ${currentConditions.main.lowTemperature}°"
    val highTemperature = "High ${currentConditions.main.highTemperature}°"
    val humidity = "Humidity ${currentConditions.main.percentHumidity} %"
    val pressure = "Pressure ${currentConditions.main.pressure} hPa"

    val iconId = currentConditions.weather[0].iconId
    val description = currentConditions.weather[0].description
    val weather = currentConditions.weather[0]

    val cityInformation = if (state != null) "$city, $state" else "$city, $country"

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
                text = cityInformation,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxSize().padding(bottom = 0.dp),
                textAlign = TextAlign.Center
            )
        }
        // Row containing the temperature and weather icon
        item {
            androidx.compose.foundation.layout.Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                // Column for temperature data
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier
                        .weight(0.6f) // Reduce weight so the column doesn't use too much space
                        .padding(SmallPadding)
                ) {
                    Text(
                        text = currentTemperature,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center, // Align to start for compact content
                        modifier = Modifier.fillMaxWidth().padding(bottom = DefaultPadding)
                    )
                    Text(
                        text = temperatureFeelsLike,
                        style = MaterialTheme.typography.bodySmall, // Keep font size consistent
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(bottom = DefaultPadding)
                    )
                }

                // Weather icon
                Box(
                    modifier = Modifier
                        .weight(0.4f)
//                        .padding(SmallPadding)
                        .align(CenterVertically)
                ) {
                    WeatherIcon(iconId, backgroundColor = White, iconSize = LargeIconSize)
                }
            }
        }
        item {
            Text(
                text = lowTemperature,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding)
            )
        }
        item {
            Text(
                text = highTemperature,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding)
            )
        }
        item {
            Text(
                text = humidity,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding)
            )
        }
        item {
            Text(
                text = pressure,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(SmallerPadding)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastDetailsView(currentConditions: CurrentConditions) {

    val city = currentConditions.city
    val state = currentConditions.state
    val country = currentConditions.sys.country

    val description = currentConditions.weather.getOrNull(0)?.description ?: "No description available"

    val latitude = currentConditions.coordinates?.latitude ?: "Unknown Latitude"
    val longitude = currentConditions.coordinates?.longitude ?: "Unknown Longitude"

    val windSpeed = currentConditions.wind.speed ?: "Unknown speed"
    val windDirection = currentConditions.wind.direction ?: "Unknown direction"
    val windGust = currentConditions.wind.gust ?: "Unknown gust"
    val percentCloudiness = "${currentConditions.clouds?.all ?: "N/A"} % cloudiness"
    val visibility = currentConditions.visibility?.toString() ?: "N/A visibility"
    val sunrise = "sunrise: ${currentConditions.sys.sunrise}" //?.let { "$it sunrise" } ?: "No sunrise data"
    val sunset = "sunset: ${currentConditions.sys.sunset}"  //?.let { "$it sunset" } ?: "No sunset data"

    val location = if (state != null) "$city, $state" else "$city, $country"
    val heading  = "$location Weather Details"
    val coordinates = "latitude: ${currentConditions.coordinates.latitude} longitude  ${currentConditions.coordinates.longitude}"

    LazyColumn(
        modifier = Modifier
            .padding(DefaultPadding)
            .fillMaxSize()
            .background(PowderBlueGray)
    ) {
        item {
            // City needs to be centered. Fills the width of it's center
            Text(
                text = heading,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
                    .padding(DefaultPadding).
                    background(DefaultHeadingColor),
                textAlign = TextAlign.Center
            )
        }
        item {
            // City needs to be centered. Fills the width of it's center
            Text(
                text = coordinates,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
                    .padding(DefaultPadding).
                    background(DefaultBoxColor),
                textAlign = TextAlign.Start
            )
        }
        item {
            // City needs to be centered. Fills the width of it's center
            Text(
                text = "$sunrise $sunset",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
                    .padding(DefaultPadding).
                    background(SandLighter),
                textAlign = TextAlign.Start
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