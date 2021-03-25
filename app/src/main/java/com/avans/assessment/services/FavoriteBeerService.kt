package com.avans.assessment.services

import android.content.Context
import androidx.room.Room
import com.avans.assessment.db.AppDatabase
import com.avans.assessment.db.entities.FavoriteBeer
import com.avans.assessment.models.Beer
import android.content.Intent
import android.os.Environment

import androidx.core.content.ContextCompat.startActivity
import java.lang.ref.WeakReference
import java.io.File





class FavoriteBeerService(ctx: Context) {
    private val context = WeakReference(ctx)
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

    fun share(beer: FavoriteBeer) {
        val ctx = context.get() ?: return  // TODO: Show error message.

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, beer.name)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, "Share your beer!")
        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(ctx, shareIntent, null)
    }
}
