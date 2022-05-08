package com.example.core_data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core_data.room.entities.CatEntity

@Dao
interface CatsFavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCat(cat: CatEntity)

    @Delete
    suspend fun removeCat(cat: CatEntity)

    @Query("SELECT * FROM CatEntity")
    fun getAllFavoriteCats(): List<CatEntity>
}