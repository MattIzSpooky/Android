package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import com.avans.assessment.ui.components.BeerListItem
import com.avans.assessment.ui.components.CenteredProgressIndicator
import com.avans.assessment.viewmodels.BeerListViewModel

@Composable
fun HomeScreen(context: Context){
    val beerListViewModel = BeerListViewModel(context)

    BeerList(beerListViewModel)
}

@Composable
fun BeerList(beerListViewModel: BeerListViewModel) {
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
                    BeerListItem(beer, onClick = {
                        // TODO: Navigate to detail page.
                        print(it.name)
                        beerListViewModel.favoriteBeer(beer)
                    })

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