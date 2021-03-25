package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService

class RandomBeerViewModel(ctx: Context) : ViewModel() {
    private val beerService = BeerService(ctx)

    init {
        loadRandomBeer()
    }

    var beer: Beer? by mutableStateOf(null)

    private fun loadRandomBeer(){
        beerService.fetchRandom {
            beer = it
        }
    }
}