package com.example.cats_favorites_common.di

import android.content.Context
import com.example.cats_favorites_common.domain.interactors.GetAllFavoriteCats
import com.example.cats_favorites_common.domain.interactors.RemoveCat
import com.example.cats_favorites_common.domain.interactors.SaveCat
import com.example.cats_favorites_common.domain.interactors.impl.GetAllFavoriteCatsImpl
import com.example.cats_favorites_common.domain.interactors.impl.RemoveCatImpl
import com.example.cats_favorites_common.domain.interactors.impl.SaveCatImpl
import com.example.core_di.scopes.PerPage
import dagger.Module
import dagger.Provides

@Module
object CatsFavoritesCommonModule {

    @Provides
    @JvmStatic
    @PerPage
    fun provideSaveCat(
        context: Context
    ): SaveCat {
        return SaveCatImpl(
            context = context
        )
    }

    @Provides
    @JvmStatic
    @PerPage
    fun provideGetAllFavoriteCats(
        context: Context
    ): GetAllFavoriteCats {
        return GetAllFavoriteCatsImpl(
            context = context
        )
    }

    @Provides
    @JvmStatic
    @PerPage
    fun provideRemoveCat(
        context: Context
    ): RemoveCat {
        return RemoveCatImpl(
            context = context
        )
    }

}