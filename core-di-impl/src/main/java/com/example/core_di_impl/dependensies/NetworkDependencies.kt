package com.example.core_di_impl.dependensies

import com.example.core_di.qualifiers.NetworkGson
import com.google.gson.Gson
import retrofit2.Retrofit

interface NetworkDependencies {

    val retrofit: Retrofit

    @get:NetworkGson
    val networkGson: Gson

}