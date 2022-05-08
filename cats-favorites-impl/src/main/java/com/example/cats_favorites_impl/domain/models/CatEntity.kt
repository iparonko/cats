package com.example.cats_favorites_impl.domain.models

data class CatEntity(
    val id: String?,
    val url: String?,
    var isFavorite: Boolean = true
)
