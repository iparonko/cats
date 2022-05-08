package com.example.core_data.retrofit.interceptors

import com.example.core_data.BuildConfig
import com.example.core_di.scopes.PerApplication
import com.example.utils.const.DataConst
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

@PerApplication
class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header(DataConst.HEADER_X_API_KEY, BuildConfig.X_API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}
