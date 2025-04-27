package com.lawal.banji.yahewa.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lawal.banji.yahewa.destination.HomeScreen
import com.lawal.banji.yahewa.view.model.ForecastViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    forecastViewModel: ForecastViewModel,
    startDestination: String = Screens.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screens.Home.route) {
            HomeScreen(
                forecastState = forecastViewModel.forecastState.collectAsState().value,
                onNavigate = {},
                onZipcodeEntered = { zipcode ->
                    forecastViewModel.setZipcode(zipcode)
                }
            )
        }
    }
}
