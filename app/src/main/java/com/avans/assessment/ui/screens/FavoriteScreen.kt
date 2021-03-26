package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.avans.assessment.viewmodels.FavoriteBeersViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.avans.assessment.ui.components.BottomNavBar
import com.avans.assessment.ui.components.general.Centered
import com.avans.assessment.ui.components.FavoriteBeerListItem

@Composable
fun FavoriteScreen(context: Context, navController: NavHostController) {
    val favoriteBeersViewModel = FavoriteBeersViewModel(context)

    Scaffold(
        bottomBar = { BottomNavBar(context, navController) },
        topBar = { TopAppBar(title = { Text("Favorites") }) }
    ) {
        val error = favoriteBeersViewModel.error

        if (error != null) {
            ErrorScreen(error)
            return@Scaffold
        }

        FavoriteBeerList(context, favoriteBeersViewModel, navController)
    }
}

@Composable
fun FavoriteBeerList(
    context: Context,
    favoriteBeersViewModel: FavoriteBeersViewModel,
    navController: NavHostController
) {
    val favorites = favoriteBeersViewModel.favoriteBeers

    if (favorites.isEmpty()) {
        Centered {
            Text("You have no favorite beers.")
        }
        return;
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(favorites) { favorite ->
                FavoriteBeerListItem(
                    context,
                    favorite,
                    onClick = {
                        navController.navigate("detail/${it.id}")
                    },
                    onDoubleTap = favoriteBeersViewModel::unfavorite,
                    onLongPress = favoriteBeersViewModel::share
                )
            }
        }
    }
}