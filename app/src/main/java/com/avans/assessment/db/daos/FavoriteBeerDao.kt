package com.avans.assessment.db.daos

import androidx.room.*
import com.avans.assessment.db.entities.FavoriteBeer

@Dao
interface FavoriteBeerDao {
    @Query("SELECT * FROM favoritebeer")
    suspend fun getAll(): List<FavoriteBeer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: FavoriteBeer)

    @Delete
    suspend fun delete(favorite: FavoriteBeer)
}