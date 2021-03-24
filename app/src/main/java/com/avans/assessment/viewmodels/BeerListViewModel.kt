package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService
import androidx.compose.runtime.setValue
import com.avans.assessment.db.entities.FavoriteBeer
import com.avans.assessment.services.FavoriteBeerService
import kotlinx.coroutines.launch
import kotlinx.coroutines.*

class BeerListViewModel(ctx: Context) : ViewModel() {
    private val beerService = BeerService(ctx)
    private val favoriteBeerService = FavoriteBeerService(ctx)

    var beers: List<Beer> by mutableStateOf(listOf())
        private set

    private var page = 1
    private val perPage = 50

    init {
        loadBeers()
    }

    fun loadBeers(callBack: (() -> Unit)? = null) {
        beerService.fetchBeers(page = page, perPage = perPage) {
            if (it.isNotEmpty()) {
                beers = (beers + it).sortedBy { beer -> beer.id }
            }

            if (callBack != null) {
                callBack()
            }
        }

        page += 1
    }

    fun favoriteBeer(beer: Beer) {
        beerService.sendNotification(beer)

        GlobalScope.launch {
            favoriteBeerService.insert(beer)
        }
    }
}