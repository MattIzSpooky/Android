package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService

class BeerViewModel(ctx: Context, id: String) : ViewModel() {
    private val beerService = BeerService(ctx)
    private val beerId = id

    init {
        loadBeer()
    }

    var beer: Beer? by mutableStateOf(null)
        private set

    private fun loadBeer() {
        beerService.fetchBeer(beerId) {
            beer = it
        }
    }

    fun loadRandomBeer(){
        beerService.fetchRandom {
            beer = it
        }
    }
}