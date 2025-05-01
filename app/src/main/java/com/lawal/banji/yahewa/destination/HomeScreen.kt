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
import com.lawal.banji.yahewa.display.ForecastView
import com.lawal.banji.yahewa.input.ZipcodeInput
import com.lawal.banji.yahewa.model.CurrentConditionsState
import com.lawal.banji.yahewa.navigation.NavigationEvent
import com.lawal.banji.yahewa.ui.theme.White


@Composable
fun HomeScreen(
    currentConditionsState: CurrentConditionsState,
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
                .background(White)
        ) {
            when (currentConditionsState) {
                is CurrentConditionsState.Loading -> {
                   Text(text = "Loading currentConditions...")
                }
                is CurrentConditionsState.Error -> {
                    val errorMessage = "Yahewa currentConditions state error: ${(currentConditionsState as CurrentConditionsState.Error).message}"
                    Text(text = errorMessage)
                }
                is CurrentConditionsState.Success -> {
                   ForecastView(currentConditionsState.currentConditions)
                }
            }
        }
    }
}