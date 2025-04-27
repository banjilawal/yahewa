package com.lawal.banji.yahewa.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lawal.banji.yahewa.display.ForecastDetailsView
import com.lawal.banji.yahewa.input.ZipcodeInput
import com.lawal.banji.yahewa.ui.theme.Orange
import com.lawal.banji.yahewa.view.model.ForecastState

@Composable
fun DetailsScreen(
    forecastState: ForecastState,
    itemId: String,
    onZipcodeEntered: (String) -> Unit
) {
    // Fail fast during development if itemId is not valid
    require(itemId.isNotBlank()) { "itemId must not be blank" }

    Scaffold(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = { ZipcodeInput(onZipcodeEntered = onZipcodeEntered) },
        bottomBar = {},
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Orange) // Consider switching to MaterialTheme for proper color usage
        ) {
            // Display the itemId at the top
            Text(text = "Details for item $itemId")

            // Render different states of the forecast
            when (forecastState) {
                is ForecastState.Loading -> {
                    // Improved loading feedback
                    CircularProgressIndicator() // Optional: Add spinner for loading state
                    Text(text = "Loading forecast details...")
                }
                is ForecastState.Error -> {
                    // Simplified and clean error message
                    val errorMessage = "Error: ${forecastState.message}"
                    Text(text = errorMessage)
                }
                is ForecastState.Success -> {
                    // Show detailed forecast only if valid
                    ForecastDetailsView(forecast = forecastState.forecast)
                }
            }
        }
    }
}


