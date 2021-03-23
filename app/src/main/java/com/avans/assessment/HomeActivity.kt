package com.avans.assessment

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.avans.assessment.ui.theme.AvansandroidassessmentTheme
import com.avans.assessment.ui.components.NavigationAppBar
import com.avans.assessment.ui.screens.HomeScreen

class HomeActivity : AppCompatActivity() {
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this.applicationContext

        setContent {
            AvansandroidassessmentTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val scaffoldState = rememberScaffoldState()

                    val navController = rememberNavController()
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = { NavigationAppBar(name = "Hello World", scaffoldState = scaffoldState) },
                        drawerShape = RoundedCornerShape(10.dp, 10.dp),
                        drawerContent = {
                            Button(onClick = { navController.navigate("beers" ) }) {
                                Text(text = "Beers")
                            }
                        }
                    ) {
                        NavHost(navController, startDestination = "beers") {
                            composable("beers") { HomeScreen(context = context) }
                        }
                    }
                }
            }
        }
    }
}