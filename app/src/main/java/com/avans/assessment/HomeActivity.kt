package com.avans.assessment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avans.assessment.background.RandomBeerNotificationService
import com.avans.assessment.ui.Routes
import com.avans.assessment.ui.screens.ContactsScreen
import com.avans.assessment.ui.screens.DetailScreen
import com.avans.assessment.ui.screens.FavoriteScreen
import com.avans.assessment.ui.screens.HomeScreen
import com.avans.assessment.ui.theme.AvansandroidassessmentTheme


class HomeActivity : AppCompatActivity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this.applicationContext

        Intent(this, RandomBeerNotificationService::class.java).also { intent ->
            startService(intent)
        }

        setContent {
            AvansandroidassessmentTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = Routes.HOME) {
                        composable(Routes.HOME) { HomeScreen(context, navController) }
                        composable(Routes.FAVORITES) { FavoriteScreen(context, navController) }
                        composable(Routes.CONTACTS) { ContactsScreen(context, navController) }
                        composable(Routes.DETAIL) { entry ->
                            DetailScreen(
                                context,
                                navController,
                                entry.arguments?.getString("id")
                            )
                        }
                    }
                }
            }
        }
    }
}