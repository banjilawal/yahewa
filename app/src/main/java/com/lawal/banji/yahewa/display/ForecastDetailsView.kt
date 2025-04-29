package com.lawal.banji.yahewa.display

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.lawal.banji.yahewa.model.Forecast
import com.lawal.banji.yahewa.ui.theme.DefaultBoxColor
import com.lawal.banji.yahewa.ui.theme.DefaultHeadingColor
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.PowderBlueGray
import com.lawal.banji.yahewa.ui.theme.SandLighter
import com.lawal.banji.yahewa.ui.theme.SmallerPadding
import com.lawal.banji.yahewa.utils.convertLongToLocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForecastDetailsView(forecast: Forecast) {

    val city = forecast.city
    val state = forecast.state
    val country = forecast.sys.country

    val description = forecast.weather.getOrNull(0)?.description ?: "No description available"

    val latitude = forecast.coordinates?.latitude ?: "Unknown Latitude"
    val longitude = forecast.coordinates?.longitude ?: "Unknown Longitude"

    val windSpeed = forecast.wind.speed ?: "Unknown speed"
    val windDirection = forecast.wind.direction ?: "Unknown direction"
    val windGust = forecast.wind.gust ?: "Unknown gust"
    val percentCloudiness = "${forecast.clouds?.all ?: "N/A"} % cloudiness"
    val visibility = forecast.visibility?.toString() ?: "N/A visibility"
    val sunrise = "sunrise: ${convertLongToLocalDateTime(forecast.sys.sunrise)}" //?.let { "$it sunrise" } ?: "No sunrise data"
    val sunset = "sunset: ${convertLongToLocalDateTime(forecast.sys.sunset)}"  //?.let { "$it sunset" } ?: "No sunset data"

    val location = if (state != null) "$city, $state" else "$city, $country"
    val heading  = "$location Weather Details"
    val coordinates = "latitude: ${forecast.coordinates.latitude} longitude  ${forecast.coordinates.longitude}"

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