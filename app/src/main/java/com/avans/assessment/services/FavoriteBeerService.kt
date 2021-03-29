package com.avans.assessment.services

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.room.Room
import com.avans.assessment.db.AppDatabase
import com.avans.assessment.db.entities.FavoriteBeer
import com.avans.assessment.models.Beer

class FavoriteBeerService(ctx: Context) : BaseService(ctx) {
    private val db = Room
        .databaseBuilder(ctx, AppDatabase::class.java, "beer-database")
        .build()

    private val favoriteBeerDao = db.favoriteBeerDao()

    suspend fun getAll(): List<FavoriteBeer> {
        return favoriteBeerDao.getAll()
    }

    suspend fun insert(beer: Beer) {
        val favorite = FavoriteBeer(
            beer.id,
            beer.name,
            beer.tagline,
            beer.firstBrewed,
            beer.imageUrl
        )

        favoriteBeerDao.insert(favorite)
    }

    suspend fun delete(favoriteBeer: FavoriteBeer) {
        return favoriteBeerDao.delete(favoriteBeer)
    }

    fun share(beer: FavoriteBeer) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, beer.name)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Share your beer!")
        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(retrieveContextOrThrow(), shareIntent, null)
    }

    fun closeConnection() {
        db.close()
    }
}
