package com.example.cats_favorites_common.domain.interactors

interface RemoveCat {
    suspend fun execute(catId: String?, imageUrl: String?)
}