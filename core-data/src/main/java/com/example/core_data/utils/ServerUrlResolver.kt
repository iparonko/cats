package com.example.core_data.utils

private const val API_URL_TEMPLATE = "https://api.%s/"

private val serverUrl: String
    get() = "thecatapi.com"

object ServerUrlResolver {
    fun getApiUrl(): String {
        return API_URL_TEMPLATE.format(serverUrl)
    }
}
