package com.lawal.banji.yahewa.navigation

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
import com.lawal.banji.yahewa.view.model.ForecastViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    forecastViewModel: ForecastViewModel,
    startDestination: String = Screens.Home.route
) {
    // Collect forecast state once, at the top level
    val forecastState = forecastViewModel.forecastState.collectAsState().value

    Scaffold(
        floatingActionButton = {
            // Floating Action Button for navigation
            // Use dynamic text/actions based on the current destination
            FloatingActionButton(
                onClick = {
                    val currentRoute = navController.currentBackStackEntry?.destination?.route
                    if (currentRoute == Screens.Home.route) {
                        // Navigate to DetailsScreen with a sample itemId
                        navController.navigate(Screens.Details.createRoute(itemId = "12345"))
                    } else {
                        // Navigate back to HomeScreen
                        navController.navigate(Screens.Home.route) {
                            popUpTo(Screens.Home.route) { inclusive = true }
                        }
                    }
                }
            ) {
                // Change button text/icon based on the current route
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                Text(if (currentRoute == Screens.Home.route) "Details" else "Home")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding) // Handle padding for the inner content
        ) {
            // HomeScreen Composable
            composable(Screens.Home.route) {
                HomeScreen(
                    forecastState = forecastState,
                    onNavigate = { itemId ->
                        itemId?.let {
                            navController.navigate(Screens.Details.createRoute(it.toString()))
                        } ?: navController.popBackStack() // Handle null gracefully
                    },
                    onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
                )
            }

            // DetailsScreen Composable
            composable(
                route = Screens.Details.route,
                arguments = listOf(
                    navArgument("itemId") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getString("itemId")
                if (itemId == null) {
                    // Handle missing itemId gracefully
                    navController.popBackStack()
                    return@composable
                }

                DetailsScreen(
                    forecastState = forecastState,
                    itemId = itemId,
                    onZipcodeEntered = { zipcode -> forecastViewModel.setZipcode(zipcode) }
                )
            }
        }
    }
}



