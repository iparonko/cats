package com.example.cats_list_impl.data.repositories

import com.example.cats_list_impl.data.models.CatDTO
import com.example.cats_list_impl.types.OrderType
import com.example.core_data.retrofit.coroutines.CoroutineCall
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesSearchApiInterface {

    @GET("v1/images/search")
    fun getImagesSearch(
        @Query("limit") limit: Int,
        @Query("order") orderType: String?,
        @Query("page") page: Int?
    ): CoroutineCall<List<CatDTO>>

}