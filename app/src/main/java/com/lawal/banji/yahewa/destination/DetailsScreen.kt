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
import com.lawal.banji.yahewa.model.CurrentConditionsState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailsScreen(
    currentConditionsState: CurrentConditionsState,
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

            // Render different states of the currentConditions
            when (currentConditionsState) {
                is CurrentConditionsState.Loading -> {
                    // Improved loading feedback
                    CircularProgressIndicator() // Optional: Add spinner for loading state
                    Text(text = "Loading currentConditions details...")
                }
                is CurrentConditionsState.Error -> {
                    // Simplified and clean error message
                    val errorMessage = "Error: ${currentConditionsState.message}"
                    Text(text = errorMessage)
                }
                is CurrentConditionsState.Success -> {
                    // Show detailed currentConditions only if valid
                    ForecastDetailsView(currentConditions = currentConditionsState.currentConditions)
                }
            }
        }
    }
}


