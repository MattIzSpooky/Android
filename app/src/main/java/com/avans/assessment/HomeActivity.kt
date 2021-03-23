package com.avans.assessment

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.avans.assessment.ui.theme.AvansandroidassessmentTheme
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val testViewModel = TestViewModel(this.applicationContext)
        
        setContent {
            AvansandroidassessmentTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val scaffoldState = rememberScaffoldState()
                    val scope = rememberCoroutineScope()
                    val navController = rememberNavController()
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBar(
                                title = { Text(text = "AppBar") },
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            scope.launch { if (scaffoldState.drawerState.isOpen) scaffoldState.drawerState.close() else scaffoldState.drawerState.open() }
                                        }
                                    ) {
                                        Icon(Icons.Default.Menu, "Menu button")
                                    }
                                }
                            )
                        },
                        drawerShape = RoundedCornerShape(10.dp, 10.dp),
                        drawerContent = {
                            Button(onClick = { navController.navigate("home") }) {
                                Text(text = "Home")
                            }

                            Button(onClick = { navController.navigate("favorites") }) {
                                Text(text = "Favorites")
                            }
                        }
                    ) {
                        NavHost(navController, startDestination = "home") {
                            composable("home") { NewsStory(navController = navController) }
                            composable("favorites") { Test(testViewModel = testViewModel, action = { testViewModel.loadData() }) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Test(testViewModel: TestViewModel, action: () -> Unit) {
    val name: String by testViewModel.data.observeAsState(initial = "")
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = action) {
            Text("Fetch me")
        }

        if (name.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally))
        } else {
            Text(text = name)
        }
    }
}

@Composable
fun NewsStory(navController: NavController) {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                "A day wandering through the sandhills " +
                        "in Shark Fin Cove, and a few of the " +
                        "sights I saw",
                style =  MaterialTheme.typography.h6,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                "Davenport, California",
                style =  MaterialTheme.typography.body2
            )
            Text(
                "December 2018",
                style =  MaterialTheme.typography.body2
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeActivity()
//    ComposeTutorialTheme {
//        NewsStory()
//    }
}
