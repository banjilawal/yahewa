package com.lawal.banji.yahewa.navigation

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Hourly : Screens("hourly")
    object Details : Screens("details/{itemId}") {
        fun createRoute(itemId: String) = "details/$itemId"
    }
}
