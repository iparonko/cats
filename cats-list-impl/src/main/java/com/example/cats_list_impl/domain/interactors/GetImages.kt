package com.example.cats_list_impl.domain.interactors

import com.example.cats_list_impl.domain.models.ContentEntity
import com.example.core_data_types.error.NetworkError

interface GetImages {
    suspend fun execute(page: Int?): Result

    sealed class Result {
        class Success(val contentEntity: ContentEntity) : Result()
        data class Error(val networkError: NetworkError) : Result()
    }
}