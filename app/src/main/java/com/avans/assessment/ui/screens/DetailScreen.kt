package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.avans.assessment.models.Beer
import com.avans.assessment.ui.components.BottomNavBar
import com.avans.assessment.ui.components.Centered
import com.avans.assessment.ui.components.CenteredProgressIndicator
import com.avans.assessment.ui.theme.Typography
import com.avans.assessment.viewmodels.BeerViewModel

@Composable
fun DetailScreen(context: Context, navController: NavHostController, id: String?) {
    if (id == null) {
        CenteredProgressIndicator()
    } else {
        val beerViewModel = BeerViewModel(context, id)

        Scaffold(
            bottomBar = { BottomNavBar(context,navController) },
            topBar = {
                TopAppBar(
                    title = { Text("${beerViewModel.beer?.name}") },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(Icons.Filled.ArrowBack, "back")
                        }
                    },
                )
            }
        ) {
            if (beerViewModel.beer == null) {
                CenteredProgressIndicator()
            } else {
                BeerDetail(beerViewModel.beer!!)
            }
        }
    }
}

@Composable
fun DetailScreenWithoutBottom(context: Context, navController: NavHostController, id: String?) {
    if (id == null) {
        CenteredProgressIndicator()
        return
    }
    val beerViewModel = BeerViewModel(context, id)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${beerViewModel.beer?.name}") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "back")
                    }
                },
            )
        }
    ) {
        if (beerViewModel.beer == null) {
            CenteredProgressIndicator()
        } else {
            BeerDetail(beerViewModel.beer!!)
        }
    }
}

@Composable
fun BeerDetail(beer: Beer) {
    Column(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
    ) {
        Card(
            elevation = 2.dp,
            modifier = Modifier.padding(10.dp),
            backgroundColor = Color.LightGray
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text(beer.name, style = Typography.h6)
                Text(beer.tagline, style = Typography.subtitle1)
                Text(beer.firstBrewed, style = Typography.caption)
            }
        }

        Spacer(Modifier.size(5.dp))

        Card(
            elevation = 2.dp,
            modifier = Modifier.padding(10.dp),
            backgroundColor = Color.LightGray
        ) {
            Column(modifier = Modifier.padding(15.dp)) {
                Text("Description:", style = Typography.h6)
                Text(beer.description, style = Typography.body1)
            }
        }
    }
}