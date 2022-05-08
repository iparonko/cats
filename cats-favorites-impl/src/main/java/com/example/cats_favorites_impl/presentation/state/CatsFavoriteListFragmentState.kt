package com.example.cats_favorites_impl.presentation.state

import com.example.core_data_types.error.NetworkError

/**
 * Состояние экрана
 */
sealed class CatsFavoriteListFragmentState {

    /**
     * Показать шиммеры
     */
    object ShowShimmers : CatsFavoriteListFragmentState()

    /**
     * Показать контент
     */
    object ShowContent : CatsFavoriteListFragmentState()

    /**
     * Показать ошибку
     * @property networkError - ошибка
     */
    data class ShowError(
        val networkError: NetworkError
    ) : CatsFavoriteListFragmentState()
}