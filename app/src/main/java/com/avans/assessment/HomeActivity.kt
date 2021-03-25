package com.avans.assessment

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avans.assessment.foreground.RandomBeerNotificationService
import com.avans.assessment.ui.screens.DetailScreen
import com.avans.assessment.ui.theme.AvansandroidassessmentTheme
import com.avans.assessment.ui.screens.FavoriteScreen
import com.avans.assessment.ui.screens.HomeScreen
import com.avans.assessment.ui.screens.RandomScreen
import android.R




class HomeActivity : AppCompatActivity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this.applicationContext

        Intent(this, RandomBeerNotificationService::class.java).also { intent ->
            startService(intent)
        }

        val randomScreenIntent = Intent(this, RandomActivity::class.java)

        setContent {
            AvansandroidassessmentTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "home") {
                        composable("home") { HomeScreen(context, navController) }
                        composable("favorites") { FavoriteScreen(context, navController) }
                        composable("random") { startActivity(randomScreenIntent) }
                        composable("detail/{id}") { entry -> DetailScreen(context, navController, entry.arguments?.getString("id")) }
                    }
                }
            }
        }
    }
}