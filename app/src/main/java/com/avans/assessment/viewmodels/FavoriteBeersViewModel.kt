package com.avans.assessment.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.avans.assessment.db.entities.FavoriteBeer
import com.avans.assessment.services.FavoriteBeerService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteBeersViewModel(ctx: Context) : ViewModel() {
    private val favoriteBeerService = FavoriteBeerService(ctx)

    var favoriteBeers: List<FavoriteBeer> by mutableStateOf(listOf())
        private set

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        GlobalScope.launch {
            favoriteBeers = favoriteBeerService.getAll()
        }
    }

    fun unfavorite(beer: FavoriteBeer) {
        favoriteBeers = favoriteBeers.toMutableList().also {
            it.remove(beer)
        }

        GlobalScope.launch {
            favoriteBeerService.delete(beer)
        }
    }
}