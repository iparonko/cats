package com.example.core_data_types.error

sealed class NetworkError {
    data class UnknownError(val message: String?) : NetworkError()
    object NoInternetConnectionError : NetworkError()
}