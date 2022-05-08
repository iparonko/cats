package com.example.cats_list_impl.presentation.state

import com.example.cats_list_impl.domain.models.ContentEntity
import com.example.core_data_types.error.NetworkError

/**
 * Состояние экрана
 */
sealed class CatsListFragmentState {

    /**
     * Показать шиммеры
     */
    object ShowShimmers : CatsListFragmentState()

    /**
     * Показать контент
     */
    object ShowContent : CatsListFragmentState()

    /**
     * Показать ошибку
     * @property networkError - ошибка
     */
    data class ShowError(
        val networkError: NetworkError
    ) : CatsListFragmentState()
}