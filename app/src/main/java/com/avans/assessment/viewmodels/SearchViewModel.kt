package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService

class SearchViewModel(ctx: Context, initialSearchText: String = "") : ViewModel() {
    private val beerService = BeerService(ctx)

    var searchText: String = initialSearchText
        private set

    var searchResults: List<Beer> by mutableStateOf(listOf())
        private set

    fun search(searchVal: String) {
        searchText = searchVal

        beerService.search(searchText) {
            if (it.isNotEmpty()) {
                searchResults = it
            }
        }
    }
}