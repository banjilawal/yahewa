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
import com.lawal.banji.yahewa.display.ForecastListView
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.query.ForecastResponseState
import com.lawal.banji.yahewa.ui.theme.White

@Composable
fun PredictionsScreen(
    forecastResponseState: ForecastResponseState, // Updated state type
    onNavigate: (NavigationEvent) -> Unit = {},
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        topBar = {}, // No toolbar specified for this screen
        bottomBar = {}, // No bottom bar specified for this screen
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(White)
        ) {
            when (forecastResponseState) {
                is ForecastResponseState.Loading -> {
                    Text(text = "Loading forecast predictions...")
                }
                is ForecastResponseState.Error -> {
                    val errorMessage = "Prediction error: ${(forecastResponseState as ForecastResponseState.Error).message}"
                    Text(text = errorMessage)
                }
                is ForecastResponseState.Success -> {
                    ForecastListView(forecastResponseState.forecastResponse) // Pass the forecast response
                }
            }
        }
    }
}
