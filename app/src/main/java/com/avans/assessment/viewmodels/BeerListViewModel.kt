package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.avans.assessment.exceptions.NoInternetException
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService
import com.avans.assessment.services.FavoriteBeerService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BeerListViewModel(ctx: Context) : ApplicationViewModel() {
    private val beerService = BeerService(ctx)
    private val favoriteBeerService = FavoriteBeerService(ctx)

    var beers: List<Beer> by mutableStateOf(listOf())
        private set

    var isFetching: Boolean by mutableStateOf(false)
        private set

    private var page = 1
    private val perPage = 50
    private var canLoadMore = true

    init {
        loadBeers()
    }

    fun loadBeers() {
        if (!canLoadMore) return

        isFetching = true

        try {
            beerService.fetchBeers(page = page, perPage = perPage, onResponse = {
                if (it.isNotEmpty()) {
                    beers = (beers + it).sortedBy { beer -> beer.id }
                } else {
                    canLoadMore = false
                }

                isFetching = false
            }, onError = {
                error = it

                isFetching = false
            })

            page += 1
        } catch (nullPointerException: NullPointerException) {
            handleException(nullPointerException)
        } catch (noInternetException: NoInternetException) {
            handleException(noInternetException)
        }
    }

    private fun handleException(exception: Exception) {
        error = exception.message
        isFetching = false
    }

    fun favoriteBeer(beer: Beer) {
        GlobalScope.launch {
            favoriteBeerService.insert(beer)
        }
    }
}