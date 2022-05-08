package com.example.cats_favorites_common.domain.interactors

interface SaveCat {
    suspend fun execute(catId: String?, imageUrl: String?)
}