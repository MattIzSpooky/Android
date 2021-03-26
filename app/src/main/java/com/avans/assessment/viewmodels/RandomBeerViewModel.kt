package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.avans.assessment.exceptions.NoInternetException
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService

class RandomBeerViewModel(ctx: Context) : ApplicationViewModel() {
    private val beerService = BeerService(ctx)

    init {
        loadRandomBeer()
    }

    var beer: Beer? by mutableStateOf(null)

    private fun loadRandomBeer() {
        try {
            beerService.fetchRandom(onResponse = {
                beer = it
            }, onError = {
                error = "Could not load random beer."
            })
        } catch (nullPointerException: NullPointerException) {
            error = nullPointerException.message
        } catch (noInternetException: NoInternetException) {
            error = noInternetException.message
        }
    }
}