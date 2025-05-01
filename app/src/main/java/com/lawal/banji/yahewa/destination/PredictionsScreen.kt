package com.lawal.banji.yahewa.destination

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lawal.banji.yahewa.input.ZipcodeInput
import com.lawal.banji.yahewa.model.PredictionGroupState
import com.lawal.banji.yahewa.model.WeatherPrediction
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.BattleShipGrayBlue
import com.lawal.banji.yahewa.ui.theme.DefaultCornerRadius
import com.lawal.banji.yahewa.ui.theme.DefaultDisplayBackgroundColor
import com.lawal.banji.yahewa.ui.theme.DefaultPadding
import com.lawal.banji.yahewa.ui.theme.Peach
import com.lawal.banji.yahewa.ui.theme.PowderBlue

@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun PredictionComposable(weatherPrediction: WeatherPrediction, modifier: Modifier = Modifier) {

    val precipitationProbability = "${weatherPrediction.precipitationProbability} % chance of rain"
    val day  = "sunrise: ${weatherPrediction.sunrise} sunset: ${weatherPrediction.sunset}"
    val humidity = "${weatherPrediction.humidity} % humidity"

    val weather = weatherPrediction.weather[0]
    val iconId = weatherPrediction.weather[0].iconId
    val description = weatherPrediction.weather[0].description
    val temperature = "high ${weatherPrediction.temperature.max} / low ${weatherPrediction.temperature.min}"

    LazyColumn(
        modifier = Modifier
            .padding(DefaultPadding)
            .fillMaxWidth()
            .background(BattleShipGrayBlue).clip(RoundedCornerShape(DefaultCornerRadius))
    ) {
        item {
            Text(
                text = temperature,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
                    .background(PowderBlue)
                    .clip(RoundedCornerShape(DefaultCornerRadius)),
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = day,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
                    .background(Peach)
                    .clip(RoundedCornerShape(DefaultCornerRadius)),
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = precipitationProbability,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
                    .background(PowderBlue)
                    .clip(RoundedCornerShape(DefaultCornerRadius)),
                textAlign = TextAlign.Center
            )
        }
        item {
            Text(
                text = humidity,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 0.dp)
                    .background(Peach)
                    .clip(RoundedCornerShape(DefaultCornerRadius)),
                textAlign = TextAlign.Center
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PredictionListComposable(predictions: List<WeatherPrediction>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(predictions.size) { index -> PredictionComposable(weatherPrediction = predictions[index]) }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PredictionsScreen(
    predictionGroupState: PredictionGroupState,
    onNavigate: (NavigationEvent) -> Unit = {},
    onZipcodeEntered: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = { ZipcodeInput(onZipcodeEntered = onZipcodeEntered) },
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(DefaultDisplayBackgroundColor)
        ) {
            when (predictionGroupState) {
                is PredictionGroupState.Loading -> {
                    Text(text = "Loading forecast predictions...")
                }
                is PredictionGroupState.Error -> {
                    val errorMessage = "WeatherPrediction error: ${(predictionGroupState as PredictionGroupState.Error).message}"
                    Text(text = errorMessage)
                }
                is PredictionGroupState.Success -> {
                    PredictionListComposable(predictions = predictionGroupState.predictionGroup.predictions, modifier = Modifier.weight(1f).fillMaxWidth())
//                    PredictionComposable(predictionGroupState.predictionGroup.predictions[0])
//                    PredictionGroupComposable(predictionGroup = predictionGroupState.predictionGroup)
                }
            }
        }
    }
}
