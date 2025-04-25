package com.lawal.banji.yahewa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppRouter(navController: NavHostController, startDestination: String) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("test") {  }
    }
}