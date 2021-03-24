package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.avans.assessment.viewmodels.FavoriteBeersViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.navigation.NavHostController
import com.avans.assessment.ui.components.BottomNavBar
import com.avans.assessment.ui.components.FavoriteBeerListItem

@Composable
fun FavoriteScreen(context: Context, navController: NavHostController){
    val favoriteBeersViewModel = FavoriteBeersViewModel(context)

    Scaffold(
        bottomBar =  { BottomNavBar(navController) } ,
        topBar = { TopAppBar(title = { Text("Favorites")}) }
    ){
        FavoriteBeerList(favoriteBeersViewModel)
    }
}

@Composable
fun FavoriteBeerList(favoriteBeersViewModel: FavoriteBeersViewModel) {
    val favorites = favoriteBeersViewModel.favoriteBeers

    if (favorites.isEmpty()) {
        Column {
            Text("You have no favorite beers.")
        }
        return;
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(favorites) { favorite ->
                FavoriteBeerListItem(
                    favorite,
                    onClick = favoriteBeersViewModel::unfavorite,
                    longPress = favoriteBeersViewModel::share
                )
            }
        }
    }
}