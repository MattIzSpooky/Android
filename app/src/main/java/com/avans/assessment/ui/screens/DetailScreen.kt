package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.avans.assessment.models.Beer
import com.avans.assessment.ui.components.BottomNavBar
import com.avans.assessment.ui.components.CenteredProgressIndicator
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
                    title = { Text("Beer: ${beerViewModel.beer?.name}") },
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
                title = { Text("Beer: ${beerViewModel.beer?.name}") },
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
    // TODO: Style detail page
    Column {
        Text("${beer.id}")
        Text(beer.name)
        Text(beer.description)
        Text(beer.firstBrewed)
        Text(beer.tagline)
    }
}