package com.avans.assessment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.avans.assessment.db.daos.FavoriteBeerDao
import com.avans.assessment.db.entities.FavoriteBeer

@Database(entities = [FavoriteBeer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteBeerDao(): FavoriteBeerDao
}
