package com.avans.assessment.services

import android.content.Context
import androidx.room.Room
import com.avans.assessment.db.AppDatabase
import com.avans.assessment.db.entities.FavoriteBeer
import com.avans.assessment.models.Beer

class FavoriteBeerService(ctx: Context) {
    private val db = Room.databaseBuilder(ctx, AppDatabase::class.java, "beer-database").build()

    suspend fun getAll(): List<FavoriteBeer> {
        val favoriteBeerDao = db.favoriteBeerDao()

        return favoriteBeerDao.getAll()
    }

    suspend fun insert(beer: Beer) {
        val favorite = FavoriteBeer(
            beer.id,
            beer.name,
            beer.tagline,
            beer.firstBrewed,
            beer.description,
            beer.imageUrl
        )

        val favoriteBeerDao = db.favoriteBeerDao()
        favoriteBeerDao.insert(favorite)
    }

    suspend fun delete(favoriteBeer: FavoriteBeer) {
        val favoriteBeerDao = db.favoriteBeerDao()

        return favoriteBeerDao.delete(favoriteBeer)
    }
}