package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService
import java.lang.Exception
import java.lang.NullPointerException

class RandomBeerViewModel(ctx: Context) : ApplicationViewModel() {
    private val beerService = BeerService(ctx)

    init {
        loadRandomBeer()
    }

    var beer: Beer? by mutableStateOf(null)

    private fun loadRandomBeer(){
        try {
            beerService.fetchRandom(onResponse = {
                beer = it
            }, onError = {
                error = "Could not load random beer."
            })
        } catch (e: Exception) {
            error = e.message
        }
    }
}