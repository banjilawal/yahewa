package com.lawal.banji.yahewa.navigation

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Details : Screens("details") {
        fun createRoute(itemId: String) = "details/$itemId"
    }
}
