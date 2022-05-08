package com.example.cats_favorites_common.domain.interactors

import com.example.cats_favorites_common.domain.models.CatEntity

interface GetAllFavoriteCats {
    suspend fun execute(): Result.Success

    sealed class Result {
        data class Success(val cats: List<CatEntity>)
    }
}