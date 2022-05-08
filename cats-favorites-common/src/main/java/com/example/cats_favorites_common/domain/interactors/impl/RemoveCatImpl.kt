package com.example.cats_favorites_common.domain.interactors.impl

import android.content.Context
import com.example.cats_favorites_common.domain.interactors.RemoveCat
import com.example.core_data.room.AppDataBase

class RemoveCatImpl(
    private val context: Context
) : RemoveCat {
    override suspend fun execute(catId: String?, imageUrl: String?) {
        if (catId == null || imageUrl == null) return
        AppDataBase
            .getDatabase(context)
            .catsFavoritesDao()
            .removeCat(com.example.core_data.room.entities.CatEntity(catId, imageUrl))
    }
}