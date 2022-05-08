package com.example.core_data.retrofit.coroutines

import com.example.core_data_types.error.NetworkError
import com.example.core_os.NetworkConnectionManager
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Response
import retrofit2.awaitResponse
import java.lang.reflect.Type

class CoroutineCallAdapter(
    private val returnType: Type,
    private val connectivityManager: NetworkConnectionManager
) : CallAdapter<Any, CoroutineCall<Any?>> {

    override fun adapt(call: Call<Any>): CoroutineCall<Any?> {
        return object : CoroutineCall<Any?> {
            override suspend fun await(): ApiResult<Any?> {
                return try {
                    if (!connectivityManager.isConnected()) {
                        return ApiResult.Error(NetworkError.NoInternetConnectionError)
                    }
                    val result: Response<Any> = call.awaitResponse()
                    ApiResult.Success(result.body())
                } catch (exception: Exception) {
                    when (exception) {
                        is java.net.UnknownHostException -> ApiResult.Error(NetworkError.NoInternetConnectionError)
                        is java.net.SocketTimeoutException -> ApiResult.Error(NetworkError.NoInternetConnectionError)
                        else -> ApiResult.Error(NetworkError.UnknownError(exception.message))
                    }
                }
            }
        }
    }

    override fun responseType(): Type = returnType

}
