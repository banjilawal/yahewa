package com.lawal.banji.yahewa.display

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.style.TextAlign
import com.lawal.banji.yahewa.model.ForecastResponse
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.SmallPadding
import com.lawal.banji.yahewa.ui.theme.SmallerPadding
import com.lawal.banji.yahewa.ui.theme.White

@Composable
fun ForecastListView(forecastResponse: ForecastResponse) {
    val city = forecastResponse.city.name
    val country = forecastResponse.city.country
    val latitude = "Latitude: ${forecastResponse.city.coordinates.latitude}"
    val longitude = "Longitude: ${forecastResponse.city.coordinates.longitude}"
    val numOfForecasts = "Forecast Count: ${forecastResponse.numberOfForecasts}"

    LazyColumn(
        modifier = Modifier
            .padding(DefaultPadding)
            .fillMaxSize()
            .background(White)
    ) {
        // Box containing City information
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SmallPadding)
                    .background(LightGray)
                    .padding(DefaultPadding) // Inner padding
            ) {
                Column {
                    Text(
                        text = "City: $city",
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(bottom = SmallerPadding)
                    )
                    Text(
                        text = "Country: $country",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(bottom = SmallerPadding)
                    )
                    Text(
                        text = latitude,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(bottom = SmallerPadding)
                    )
                    Text(
                        text = longitude,
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Start,
                    )
                }
            }
        }

        // Box containing the number of forecasts
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SmallPadding)
                    .background(LightGray)
                    .padding(DefaultPadding) // Inner padding
            ) {
                Text(
                    text = numOfForecasts,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
