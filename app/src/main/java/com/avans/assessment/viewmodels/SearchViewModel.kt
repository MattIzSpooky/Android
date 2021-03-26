package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.avans.assessment.exceptions.NoInternetException
import com.avans.assessment.models.Beer
import com.avans.assessment.services.BeerService

class SearchViewModel(ctx: Context, initialSearchText: String = "") : ApplicationViewModel() {
    private val beerService = BeerService(ctx)

    var searchText: String = initialSearchText
        private set

    var searchResults: List<Beer> by mutableStateOf(listOf())
        private set

    var isFetching: Boolean by mutableStateOf(false)
        private set

    fun search(searchVal: String) {
        searchText = searchVal

        if (searchText.isEmpty()) {
            searchResults = ArrayList()
            return
        }

        isFetching = true;

        try {
            beerService.search(searchText, onResponse = {
                if (it.isNotEmpty()) {
                    searchResults = it
                }

                isFetching = false;
            }, onError = {
                error = "Could not search"

                isFetching = false;
            })
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
}