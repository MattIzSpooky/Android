package com.avans.assessment.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

@Composable
fun BottomNavBar(navController: NavHostController) {
    BottomBar(
        homeListener = { route(navController, "home")  },
        favoriteListener = { route(navController, "favorites") },
        contactListener = { route(navController, "contacts") }
    )
}

private fun route(navController: NavHostController, route: String) {
    navController.navigate(route) { launchSingleTop = true }
}
