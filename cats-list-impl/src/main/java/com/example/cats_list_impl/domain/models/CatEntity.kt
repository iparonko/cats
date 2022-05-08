package com.example.cats_list_impl.domain.models

data class CatEntity(
    val id: String?,
    val url: String?,
    var isFavorite: Boolean = false
)
