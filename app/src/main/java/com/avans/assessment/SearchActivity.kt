package com.avans.assessment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avans.assessment.ui.screens.*
import com.avans.assessment.ui.screens.DetailScreen
import com.avans.assessment.ui.theme.AvansandroidassessmentTheme

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AvansandroidassessmentTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "search") {
                        composable("search") { SearchScreen(applicationContext, navController,intent.getStringExtra(Intent.EXTRA_TEXT) ?: "") }
                        composable("detail/{id}") { entry -> DetailScreenWithoutBottom(applicationContext, navController, entry.arguments?.getString("id")) }
                    }
                }
            }
        }
    }
}