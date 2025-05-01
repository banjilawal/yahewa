package com.lawal.banji.yahewa.destination

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.graphics.Color
import com.lawal.banji.yahewa.display.ForecastDetailsView
import com.lawal.banji.yahewa.input.ZipcodeInput
import com.lawal.banji.yahewa.model.ForecastState

@RequiresApi(Build.VERSION_CODES.O)
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
                .background(Color.LightGray) // Consider switching to MaterialTheme for proper color usage
        ) {
            // Display the itemId at the top
//            Text(text = "Details for item $itemId")

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


