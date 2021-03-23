package com.avans.assessment.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService

class BeerListViewModel(ctx: Context) {
    private val beerService = BeerService(ctx)
    private val _data: MutableLiveData<List<Beer>> = MutableLiveData(listOf())

    val beers: LiveData<List<Beer>> = _data

    fun loadBeers() {
        beerService.fetchBeers {
            this._data.value = it
        }
    }
}