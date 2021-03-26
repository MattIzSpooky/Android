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
import java.lang.NullPointerException

class BeerListViewModel(ctx: Context) : ApplicationViewModel() {
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
        try {
            beerService.fetchBeers(page = page, perPage = perPage, onResponse = {
                if (it.isNotEmpty()) {
                    beers = (beers + it).sortedBy { beer -> beer.id }
                }

                if (callBack != null) {
                    callBack()
                }
            }, onError = {
                error = it
            })

            page += 1
        } catch (e: NullPointerException) {
            error = e.message
        }
    }

    fun favoriteBeer(beer: Beer) {
        GlobalScope.launch {
            favoriteBeerService.insert(beer)
        }
    }
}