package com.lawal.banji.yahewa.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lawal.banji.yahewa.display.ForecastDetailsView
import com.lawal.banji.yahewa.input.ZipcodeInput
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.Orange
import com.lawal.banji.yahewa.view.model.ForecastState

@Composable
fun DetailsScreen(
    forecastState: ForecastState,
    itemId: String,
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
                .background(Orange)
        ) {
            Text(text = "Details for item $itemId")
            when (forecastState) {
                is ForecastState.Loading -> {
                    Text(text = "Loading forecast details...")
                }
                is ForecastState.Error -> {
                    val errorMessage = "Yahewa forecast state error: ${(forecastState as ForecastState.Error).message}"
                    Text(text = errorMessage)
                }
                is ForecastState.Success -> {
                    val forecast = forecastState.forecast
                    ForecastDetailsView(forecast = forecastState.forecast) { }
                }
            }
        }
    }
}
