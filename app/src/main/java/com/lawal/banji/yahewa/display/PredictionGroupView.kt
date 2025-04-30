package com.lawal.banji.yahewa.display

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.lawal.banji.yahewa.model.PredictionGroup
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.SmallPadding
import com.lawal.banji.yahewa.ui.theme.SmallerPadding
import com.lawal.banji.yahewa.ui.theme.White

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PredictionGroupView(predictionGroup: PredictionGroup) {
    val city = predictionGroup.city.name
    val country = predictionGroup.city.country
    val latitude = "Latitude: ${predictionGroup.city.coordinates.latitude}"
    val longitude = "Longitude: ${predictionGroup.city.coordinates.longitude}"
    val numOfForecasts = "Forecast Count: ${predictionGroup.numberOfForecasts}"

    LazyColumn(
        modifier = Modifier
            .padding(DefaultPadding)
            .fillMaxSize()
            .background(White)
    ) {
        // City information
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SmallPadding)
                    .background(LightGray)
                    .padding(DefaultPadding)
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

        // Forecast count
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SmallPadding)
                    .background(LightGray)
                    .padding(DefaultPadding)
            ) {
                Text(
                    text = numOfForecasts,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

//        // **This is where the `items` function goes**
//        items(predictionGroup.predictions) { weatherPrediction ->
//            // Render the prediction item using PredictionView
//            PredictionView(weatherPrediction = weatherPrediction)
//        }
    }
}

