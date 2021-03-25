package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.avans.assessment.ui.components.BeerListItem
import com.avans.assessment.ui.components.BottomNavBar
import com.avans.assessment.ui.components.CenteredProgressIndicator
import com.avans.assessment.viewmodels.BeerListViewModel

@Composable
fun HomeScreen(context: Context, navController: NavHostController){
    val beerListViewModel = BeerListViewModel(context)

    Scaffold(
        bottomBar =  { BottomNavBar(navController) },
        topBar = {
            TopAppBar(title = { Text("Beers")},)
        }
    ){
        BeerList(beerListViewModel, navController)
    }
}

@Composable
fun BeerList(beerListViewModel: BeerListViewModel, navController: NavHostController) {
    val beers = beerListViewModel.beers
    val (isFetching, setIsFetching) = remember {
        mutableStateOf(false)
    }

    if (beers.isEmpty()) {
        CenteredProgressIndicator()
        return;
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val lastIndex = beers.lastIndex

        LazyColumn {
            itemsIndexed(beers) { index, beer ->
                key(beer.id) {
                    BeerListItem(beer) {
                        navController.navigate("detail/${beer.id}")
                        // TODO: Call favorite button
                        //beerListViewModel.favoriteBeer(beer)
                    }

                    SideEffect {
                        if (lastIndex == index) {
                            setIsFetching(true)

                            beerListViewModel.loadBeers {
                                setIsFetching(false)
                            }
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