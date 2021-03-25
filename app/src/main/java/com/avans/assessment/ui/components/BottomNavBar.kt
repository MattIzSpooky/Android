package com.avans.assessment.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate

@Composable
fun BottomNavBar(navController: NavHostController){
    BottomBar(
        homeListener = { navController.navigate("home" ) },
        favoriteListener = { navController.navigate("favorites" ) },
        randomListener = { navController.navigate("random" ) },
    )
}