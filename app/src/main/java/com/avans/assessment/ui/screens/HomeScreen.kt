package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import com.avans.assessment.models.Beer
import com.avans.assessment.viewmodels.BeerListViewModel

@Composable
fun HomeScreen(context: Context){
    val beerListViewModel = BeerListViewModel(context)
    val beers: List<Beer> by beerListViewModel.beers.observeAsState(initial = emptyList())

    beerListViewModel.loadBeers()

    if (beers.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
        }
    } else {
        LazyColumn {
            items(beers) { beer ->
                Text(text = beer.name)
            }
        }
    }
}