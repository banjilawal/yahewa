package com.lawal.banji.yahewa.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lawal.banji.yahewa.destination.DetailsScreen
import com.lawal.banji.yahewa.destination.HomeScreen
import com.lawal.banji.yahewa.destination.PredictionsScreen
import com.lawal.banji.yahewa.view.model.ForecastViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    forecastViewModel: ForecastViewModel,
    startDestination: String = Screens.Home.route
) {
    // Collect forecast states
    val forecastState = forecastViewModel.forecastState.collectAsState().value
    val forecastResponseState = forecastViewModel.forecastResponseState.collectAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val currentRoute = navController.currentBackStackEntry?.destination?.route
                    // Handle FAB navigation logic
                    when (currentRoute) {
                        Screens.Home.route -> {
                            // Navigate to DetailsScreen with a sample itemId
                            navController.navigate(Screens.Details.createRoute(itemId = "12345"))
                        }

                        Screens.Details.route -> {
                            // Navigate to PredictionsScreen
                            navController.navigate(Screens.Predictions.route)
                        }

                        else -> {
                            // Navigate back to HomeScreen
                            navController.navigate(Screens.Home.route) {
                                popUpTo(Screens.Home.route) { inclusive = true }
                            }
                        }
                    }
                }
            ) {
                // Change Button Text/Icon based on Current Route
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                Text(
                    when (currentRoute) {
                        Screens.Home.route -> "Details"
                        Screens.Details.route -> "Predictions"
                        else -> "Home"
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            // HomeScreen
            composable(Screens.Home.route) {
                HomeScreen(
                    forecastState = forecastState,
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
                    forecastState = forecastState,
                    itemId = itemId,
                    onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
                )
            }

            // PredictionsScreen
            composable(Screens.Predictions.route) {
                PredictionsScreen(
                    forecastResponseState = forecastResponseState,
                    onNavigate = {
                        // Directly handle navigation without unnecessary indirection
                        navController.popBackStack()
                    }

                )
            }
        }
    }
}




