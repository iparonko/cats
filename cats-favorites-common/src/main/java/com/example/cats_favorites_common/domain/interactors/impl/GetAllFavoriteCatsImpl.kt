package com.example.cats_favorites_common.domain.interactors.impl

import android.content.Context
import com.example.cats_favorites_common.domain.interactors.GetAllFavoriteCats
import com.example.cats_favorites_common.domain.mappers.toEntity
import com.example.core_data.room.AppDataBase

class GetAllFavoriteCatsImpl(
    private val context: Context
) : GetAllFavoriteCats {
    override suspend fun execute(): GetAllFavoriteCats.Result.Success {
        val result = AppDataBase
            .getDatabase(context)
            .catsFavoritesDao()
            .getAllFavoriteCats()
            .toEntity()
        return GetAllFavoriteCats.Result.Success(result)
    }
}