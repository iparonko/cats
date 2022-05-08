package com.example.cats_favorites_impl.di.components

import com.example.cats_favorites_common.di.CatsFavoritesCommonModule
import com.example.cats_favorites_common.domain.interactors.GetAllFavoriteCats
import com.example.cats_favorites_common.domain.interactors.RemoveCat
import com.example.core_di.scopes.PerPage
import com.example.core_di_impl.dependensies.AppDependencies
import dagger.Component

@PerPage
@Component(
    modules = [
        CatsFavoritesCommonModule::class
    ], dependencies = [
        AppDependencies::class
    ]
)
interface CatsFavoriteListComponent {

    @Component.Factory
    interface Factory {
        fun create(
            appDependencies: AppDependencies
        ): CatsFavoriteListComponent
    }

    val removeCat: RemoveCat
    val getAllFavoriteCats: GetAllFavoriteCats

}