package com.example.cats_list_impl.di.modules

import com.example.cats_favorites_common.domain.interactors.GetAllFavoriteCats
import com.example.cats_list_impl.data.repositories.ImagesSearchApiInterface
import com.example.cats_list_impl.domain.interactors.GetImages
import com.example.cats_list_impl.domain.interactors.impl.GetImagesImpl
import com.example.cats_list_impl.presentation.viewmodels.CatsListViewModel
import com.example.core_di.scopes.PerPage
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object CatsListModule {

    @Provides
    @JvmStatic
    @PerPage
    fun provideImagesSearchApiInterface(
        retrofit: Retrofit
    ): ImagesSearchApiInterface {
        return retrofit.create(ImagesSearchApiInterface::class.java)
    }

    @Provides
    @JvmStatic
    @PerPage
    fun provideGetImages(
        imagesSearchApiInterface: ImagesSearchApiInterface,
        getAllFavoriteCats: GetAllFavoriteCats
    ): GetImages {
        return GetImagesImpl(
            imagesSearchApiInterface = imagesSearchApiInterface,
            getAllFavoriteCats = getAllFavoriteCats
        )
    }

}