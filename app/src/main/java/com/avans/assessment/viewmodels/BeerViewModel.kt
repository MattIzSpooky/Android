package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.avans.assessment.exceptions.NoInternetException
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService

class BeerViewModel(ctx: Context, id: String) : ApplicationViewModel() {
    private val beerService = BeerService(ctx)
    private val beerId = id

    init {
        loadBeer()
    }

    var beer: Beer? by mutableStateOf(null)

    private fun loadBeer() {
        try {
            beerService.fetchBeer(beerId, onResponse = {
                beer = it
            }, onError = {
                error = "Could not retrieve beer"
            })
        } catch (nullPointerException: NullPointerException) {
            error = nullPointerException.message
        } catch (noInternetException: NoInternetException) {
            error = noInternetException.message
        }
    }
}