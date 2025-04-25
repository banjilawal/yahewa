package com.lawal.banji.yahewa.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lawal.banji.yahewa.display.CurrentForecastView
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.Lavender
import com.lawal.banji.yahewa.ui.theme.SandLight
import com.lawal.banji.yahewa.viewmodel.ForecastState


@Composable
fun HomeScreen(
    forecastState: ForecastState,
    onNavigate: (NavigationEvent) -> Unit = {} //
) {
    Scaffold(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = {
            Text(
                modifier = Modifier.background(SandLight).fillMaxWidth(),
                style  = MaterialTheme.typography.titleLarge,
                text = stringResource(id = com.lawal.banji.yahewa.R.string.app_name))
        },
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Lavender)
        ) {
            when (forecastState) {
                is ForecastState.Loading -> {
                    Text(text = "Loading forecast...")
                }
                is ForecastState.Error -> {
                    val errorMessage = "Yahewa forecast state error: ${(forecastState as ForecastState.Error).message}"
                    Text(text = errorMessage)
                }
                is ForecastState.Success -> {
                    val forecast = forecastState.forecast
                    CurrentForecastView(forecast = forecast)
                }
            }
        }
    }
}