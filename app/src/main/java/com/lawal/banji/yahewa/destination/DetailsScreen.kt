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
//import com.lawal.banji.yahewa.display.ForecastDetailsView
import com.lawal.banji.yahewa.input.ZipCodeInput
import com.lawal.banji.yahewa.model.CurrentWeatherState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(
    currentWeatherState: CurrentWeatherState,
    itemId: String,
    onZipcodeEntered: (String) -> Unit
) {
    // Fail fast during development if itemId is not valid
    require(itemId.isNotBlank()) { "itemId must not be blank" }

    Scaffold(
        modifier = Modifier.fillMaxSize().statusBarsPadding(),
        topBar = { ZipCodeInput(onZipCodeEntered = onZipcodeEntered) },
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

            // Render different states of the currentWeather
            when (currentWeatherState) {
                is CurrentWeatherState.Loading -> {
                    // Improved loading feedback
                    CircularProgressIndicator() // Optional: Add spinner for loading state
                    Text(text = "Loading currentWeather details...")
                }
                is CurrentWeatherState.Error -> {
                    // Simplified and clean error message
                    val errorMessage = "Error: ${currentWeatherState.message}"
                    Text(text = errorMessage)
                }
                is CurrentWeatherState.Success -> {
                    // Show detailed currentWeather only if valid
//                    ForecastDetailsView(currentWeather = currentWeatherState.currentWeather)
                }
            }
        }
    }
}


