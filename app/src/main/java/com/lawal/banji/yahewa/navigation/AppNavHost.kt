package com.lawal.banji.yahewa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {  }
    }
}