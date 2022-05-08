package com.example.core_data.retrofit.coroutines

interface CoroutineCall<T> {

    suspend fun await(): ApiResult<T>
}
