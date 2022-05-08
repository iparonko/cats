package com.example.cats_favorites_common.domain.interactors.impl

import android.content.Context
import com.example.cats_favorites_common.domain.interactors.SaveCat
import com.example.core_data.room.AppDataBase


class SaveCatImpl(
    private val context: Context
) : SaveCat {
    override suspend fun execute(catId: String?, imageUrl: String?) {
        if (catId == null || imageUrl == null) return
        AppDataBase
            .getDatabase(context)
            .catsFavoritesDao()
            .addCat(com.example.core_data.room.entities.CatEntity(catId, imageUrl))
    }
}