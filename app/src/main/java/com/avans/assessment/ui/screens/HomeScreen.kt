package com.avans.assessment.ui.screens

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.avans.assessment.models.Beer
import com.avans.assessment.viewmodels.BeerListViewModel

@Composable
fun HomeScreen(context: Context){
    val beerListViewModel = BeerListViewModel(context)
    val beers: List<Beer> by beerListViewModel.beers.observeAsState(initial = emptyList())
    beerListViewModel.loadBeers()

    LazyColumn {
        items(beers) { beer ->
            Text(text = beer.name)
        }
    }
}