package com.example.cats_favorites_common.domain.mappers

import com.example.core_data.room.entities.CatEntity

fun List<CatEntity>.toEntity(): List<com.example.cats_favorites_common.domain.models.CatEntity> =
    this.map {
        it.toEntity()
    }

private fun CatEntity.toEntity(): com.example.cats_favorites_common.domain.models.CatEntity =
    com.example.cats_favorites_common.domain.models.CatEntity(
        id = id,
        url = url
    )