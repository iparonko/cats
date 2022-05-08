package com.example.cats_favorites_api

import androidx.fragment.app.Fragment
import com.example.cats_favorites_impl.presentation.views.CatsFavoriteListFragment

object CatsFavoritesFragmentFactory {

    fun getFragment(request: Any): Fragment {
        return when (request) {
            is CatsFavoritesRequest -> CatsFavoriteListFragment.newInstance()
            else -> TODO("Create global router with factories")
        }
    }

}