package com.avans.assessment.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.avans.assessment.RandomActivity
import com.avans.assessment.SettingsActivity
import com.avans.assessment.ui.Routes

@Composable
fun BottomNavBar(context: Context, navController: NavHostController){
    BottomBar(
        homeListener = { route(navController, Routes.HOME)  },
        favoriteListener = { route(navController, Routes.FAVORITES) },
        contactListener = { route(navController, Routes.CONTACTS) },
        randomListener = {
            val intent = Intent(context, RandomActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(context, intent, null)
        },
        settingsListener = {
            val intent = Intent(context, SettingsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(context, intent, null)
        }
    )
}

private fun route(navController: NavHostController, route: String) {
    navController.navigate(route) { launchSingleTop = true }
}
