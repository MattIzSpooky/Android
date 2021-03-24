package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService
import androidx.compose.runtime.setValue

class BeerListViewModel(ctx: Context) : ViewModel() {
    private val beerService = BeerService(ctx)

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
}