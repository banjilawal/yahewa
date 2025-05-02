package com.lawal.banji.yahewa.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lawal.banji.yahewa.destination.DetailsScreen
import com.lawal.banji.yahewa.destination.ForecastScreen

import com.lawal.banji.yahewa.destination.HomeScreen
import com.lawal.banji.yahewa.ui.theme.DarkGray1
import com.lawal.banji.yahewa.ui.theme.DefaultDisplayBackgroundColor
import com.lawal.banji.yahewa.view.model.ForecastViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    forecastViewModel: ForecastViewModel,
    startDestination: String = Screens.Home.route
) {
    // Collect currentWeather states
    val forecastState = forecastViewModel.currentWeatherState.collectAsState().value
    val forecastGroupState = forecastViewModel.forecastGroupState.collectAsState().value

    Scaffold(
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .fillMaxSize() // Fill the parent container to enable bottom-center alignment
            ) {
                Box(                    modifier = Modifier
                    .align(Alignment.BottomCenter) // Align the button box to the bottom center
                    .padding(horizontal = 5.dp) // Add 5.dp left and right padding
                ) {
                    FloatingActionButton(
                        onClick = {
                            val currentRoute = navController.currentBackStackEntry?.destination?.route
                            // Toggle logic: Navigate to Predictions if on Home, otherwise to Home
                            if (currentRoute == Screens.Home.route) {
                                navController.navigate(Screens.Forecasts.route)
                            } else {
                                navController.navigate(Screens.Home.route) {
                                    popUpTo(Screens.Home.route) { inclusive = true }
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter) // Correctly use `align` inside a BoxScope
                            .padding(16.dp) // Add padding from the bottom edge
                            .fillMaxWidth(0.35f)
                    ) {
                        // Set Button Text based on Current Route
                        val currentRoute = navController.currentBackStackEntry?.destination?.route
                        Text(text = "", modifier = Modifier.background(DarkGray1)
//                            if (currentRoute == Screens.Home.route) "Predictions" else "Home"
                        )
                    }
                }

            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier
                .padding(innerPadding)
                .background(DefaultDisplayBackgroundColor)
        ) {
            // HomeScreen
            composable(Screens.Home.route) {
                HomeScreen(
                    currentWeatherState = forecastState,
                    onNavigate = { itemId ->
                        navController.navigate(Screens.Details.createRoute(itemId.toString()))
                    },
                    onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
                )
            }

            // DetailsScreen
            composable(
                route = Screens.Details.route,
                arguments = listOf(
                    navArgument("itemId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")
                if (itemId == null) {
                    navController.popBackStack() // Handle missing itemId gracefully
                    return@composable
                }
                DetailsScreen(
                    currentWeatherState = forecastState,
                    itemId = itemId,
                    onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
                )
            }

            // PredictionsScreen
            composable(Screens.Forecasts.route) {
                ForecastScreen(
                    forecastGroupState = forecastGroupState,
                    onNavigate = {
                        // Direct navigation handling
                        navController.popBackStack()
                    },
                    onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) } // Add this line
                )
            }

        }
    }
}





