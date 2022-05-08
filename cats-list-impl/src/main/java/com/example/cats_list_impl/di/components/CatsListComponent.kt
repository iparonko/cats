package com.example.cats_list_impl.di.components

import com.example.cats_favorites_common.di.CatsFavoritesCommonModule
import com.example.cats_favorites_common.domain.interactors.GetAllFavoriteCats
import com.example.cats_favorites_common.domain.interactors.RemoveCat
import com.example.cats_favorites_common.domain.interactors.SaveCat
import com.example.cats_list_impl.di.modules.CatsListModule
import com.example.cats_list_impl.domain.interactors.GetImages
import com.example.core_di.scopes.PerPage
import com.example.core_di_impl.dependensies.AppDependencies
import dagger.Component

@PerPage
@Component(
    modules = [
        CatsListModule::class,
        CatsFavoritesCommonModule::class
    ], dependencies = [
        AppDependencies::class
    ]
)
interface CatsListComponent {

    @Component.Factory
    interface Factory {
        fun create(
            appDependencies: AppDependencies,
            module: CatsListModule
        ): CatsListComponent
    }

    val getImages: GetImages
    val saveCat: SaveCat
    val removeCat: RemoveCat
    val getAllFavoriteCats: GetAllFavoriteCats

}