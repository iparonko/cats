package com.example.cats_favorites_impl.data.mappers

import com.example.cats_favorites_common.domain.models.CatEntity
import com.example.cats_favorites_impl.domain.models.CatEntity as LocalCatEntity

fun List<CatEntity>.toEntity(): List<LocalCatEntity> {
    return this.map {
        it.toEntity()
    }
}

private fun CatEntity.toEntity(): LocalCatEntity = LocalCatEntity(
    id = id,
    url = url
)