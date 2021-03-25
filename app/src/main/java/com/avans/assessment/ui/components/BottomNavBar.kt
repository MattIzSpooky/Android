package com.avans.assessment.ui.components

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.avans.assessment.RandomActivity

@Composable
fun BottomNavBar(context: Context, navController: NavHostController){
    BottomBar(
        homeListener = { navController.navigate("home" ) },
        favoriteListener = { navController.navigate("favorites" ) },
        randomListener = {
            val intent = Intent(context, RandomActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(context, intent, null)
        },
    )
}