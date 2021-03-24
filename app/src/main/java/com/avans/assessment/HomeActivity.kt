package com.avans.assessment

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avans.assessment.ui.screens.DetailScreen
import com.avans.assessment.ui.theme.AvansandroidassessmentTheme
import com.avans.assessment.ui.screens.FavoriteScreen
import com.avans.assessment.ui.screens.HomeScreen

class HomeActivity : AppCompatActivity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this.applicationContext

        setContent {
            AvansandroidassessmentTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "home") {
                        composable("home") { HomeScreen(context, navController) }
                        composable("favorites") { FavoriteScreen(context, navController) }
                        composable("detail/{id}") { entry -> DetailScreen(context, navController, entry.arguments?.getString("id")) }
                    }
                }
            }
        }
    }
}