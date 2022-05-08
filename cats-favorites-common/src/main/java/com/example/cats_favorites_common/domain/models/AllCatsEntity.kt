package com.example.cats_favorites_common.domain.models

data class AllCatsEntity(
    val catsMap: MutableMap<String ,CatEntity>
)