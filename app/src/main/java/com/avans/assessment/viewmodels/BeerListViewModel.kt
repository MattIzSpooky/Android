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
import java.lang.Exception
import java.lang.NullPointerException

class BeerListViewModel(ctx: Context) : ApplicationViewModel() {
    private val beerService = BeerService(ctx)
    private val favoriteBeerService = FavoriteBeerService(ctx)

    var beers: List<Beer> by mutableStateOf(listOf())
        private set

    var isFetching: Boolean by mutableStateOf(false)
        private set

    private var page = 1
    private val perPage = 50

    init {
        loadBeers()
    }

    fun loadBeers() {
        isFetching = true

        try {
            beerService.fetchBeers(page = page, perPage = perPage, onResponse = {
                if (it.isNotEmpty()) {
                    beers = (beers + it).sortedBy { beer -> beer.id }
                }

                isFetching = false
            }, onError = {
                error = it

                isFetching = false
            })

            page += 1
        } catch (e: Exception) {
            error = e.message
            isFetching = false
        }
    }

    fun favoriteBeer(beer: Beer) {
        GlobalScope.launch {
            favoriteBeerService.insert(beer)
        }
    }
}