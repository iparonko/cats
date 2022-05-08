package com.example.core_data.retrofit.callAdapter

import com.example.core_data.retrofit.coroutines.CoroutineCall
import com.example.core_data.retrofit.coroutines.CoroutineCallAdapter
import com.example.core_os.NetworkConnectionManager
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class CallAdapterFactory(
    private val connectivityManager: NetworkConnectionManager
) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *> {
        val rawType = getRawType(returnType)

        if (rawType == CoroutineCall::class.java) {
            return CoroutineCallAdapter(
                returnType = getParameterUpperBound(
                    0,
                    (returnType as ParameterizedType)
                ),
                connectivityManager = connectivityManager
            )
        }

        throw IllegalStateException("No call adapter for $rawType")
    }
}
