package com.example.core_di_impl.modules

import android.content.Context
import com.example.core_data.retrofit.callAdapter.CallAdapterFactory
import com.example.core_data.retrofit.deserializers.UnitDeserializer
import com.example.core_data.retrofit.deserializers.ZonedDateTimeDeserializer
import com.example.core_data.retrofit.interceptors.AuthInterceptor
import com.example.core_data.utils.ServerUrlResolver
import com.example.core_di.qualifiers.BaseOkHttpClient
import com.example.core_di.qualifiers.CachedOkHttpClient
import com.example.core_di.qualifiers.CatApiOkHttpClient
import com.example.core_di.qualifiers.NetworkGson
import com.example.core_di.scopes.PerApplication
import com.example.core_os.NetworkConnectionManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @PerApplication
    @Provides
    fun provideServerUrlResolver(): ServerUrlResolver {
        return ServerUrlResolver
    }

    @PerApplication
    @Provides
    @CatApiOkHttpClient
    fun provideOkHttpClient(
        @BaseOkHttpClient baseOkHttpClient: OkHttpClient,
    ): OkHttpClient {
        return baseOkHttpClient.newBuilder().apply {
            addInterceptor(AuthInterceptor())
        }.build()
    }


    @PerApplication
    @Provides
    @BaseOkHttpClient
    fun provideBaseOkHttpClient(): OkHttpClient {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                if (message.length > 40000) {
                    return
                }
            }
        }
        val level = HttpLoggingInterceptor.Level.BODY
        val loggerInterceptor = HttpLoggingInterceptor(logger).setLevel(level)

        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(loggerInterceptor)
            .build()
    }


    @PerApplication
    @Provides
    @CachedOkHttpClient
    fun provideCachedOkHttpClient(
        @CatApiOkHttpClient okHttpClient: OkHttpClient,
        cache: Cache
    ): OkHttpClient {
        return okHttpClient.newBuilder()
            .cache(cache)
            .build()
    }

    @Provides
    @PerApplication
    fun provideOkHttpCache(context: Context): Cache {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
        val cacheDirectory = File(context.cacheDir, CACHE_DIRECTORY_NAME)
        return Cache(cacheDirectory, cacheSize)
    }

    @PerApplication
    @Provides
    @NetworkGson
    fun provideGson(): Gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .registerTypeAdapter(ZonedDateTime::class.java, ZonedDateTimeDeserializer())
        .registerTypeAdapter(Unit::class.java, UnitDeserializer())
        .create()


    @PerApplication
    @Provides
    fun provideRetrofit(
        serverUrlResolver: ServerUrlResolver,
        @CatApiOkHttpClient okHttpClient: OkHttpClient,
        @NetworkGson gson: Gson,
        connectivityManager: NetworkConnectionManager
    ): Retrofit {
        val callAdapterFactory = CallAdapterFactory(
            connectivityManager = connectivityManager,
        )

        val apiUrl = serverUrlResolver.getApiUrl()
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .build()
    }


    companion object {
        private const val CACHE_DIRECTORY_NAME = "okhttp"
    }
}
