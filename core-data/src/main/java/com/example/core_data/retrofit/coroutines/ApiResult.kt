package com.example.core_data.retrofit.coroutines

import com.example.core_data_types.error.NetworkError

sealed class ApiResult<out T> {
    class Success<T>(val value: T) : ApiResult<T>()
    class Error(val networkError: NetworkError) : ApiResult<Nothing>()
}
