package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.avans.assessment.ui.Routes
import com.avans.assessment.ui.components.BeerListItem
import com.avans.assessment.ui.components.BottomNavBar
import com.avans.assessment.ui.components.general.CenteredProgressIndicator
import com.avans.assessment.viewmodels.BeerListViewModel

@Composable
fun HomeScreen(context: Context, navController: NavHostController){
    val beerListViewModel = BeerListViewModel(context)

    Scaffold(
        bottomBar =  { BottomNavBar(context,navController) },
        topBar = {
            TopAppBar(title = { Text("Beers")})
        }
    ){
        val error = beerListViewModel.error

        if (error != null) {
            ErrorScreen(error)
            return@Scaffold
        }

        BeerList(context, beerListViewModel, navController)
    }
}

@Composable
fun BeerList(context: Context, beerListViewModel: BeerListViewModel, navController: NavHostController) {
    val beers = beerListViewModel.beers
    val isFetching = beerListViewModel.isFetching

    if (beers.isEmpty()) {
        CenteredProgressIndicator()
        return;
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val lastIndex = beers.lastIndex

        LazyColumn(modifier = Modifier.padding(bottom = 55.dp)) {
            itemsIndexed(beers) { index, beer ->
                key(beer.id) {
                    BeerListItem(context, beer, onClick = {
                        navController.navigate(Routes.compoundUrl(Routes.DETAIL, "id", it.id.toString()))
                    }, onDoubleTap = {
                        beerListViewModel.favoriteBeer(beer)
                    })

                    SideEffect {
                        if (lastIndex == index) {
                            beerListViewModel.loadBeers()
                        }
                    }
                }
            }
            if (isFetching) {
                item {
                    CenteredProgressIndicator()
                }
            }
        }
    }
}