package com.example.cats_favorites_impl.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cats_favorites_common.domain.interactors.GetAllFavoriteCats
import com.example.cats_favorites_common.domain.interactors.RemoveCat
import com.example.cats_favorites_impl.data.mappers.toEntity
import com.example.cats_favorites_impl.domain.models.CatEntity
import com.example.cats_favorites_impl.presentation.state.CatsFavoriteListFragmentState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatsFavoriteListViewModel(
    private val getAllFavoriteCats: GetAllFavoriteCats,
    private val removeCat: RemoveCat
) : ViewModel() {

    val fragmentState: StateFlow<CatsFavoriteListFragmentState>
        get() = _fragmentState

    val cats = MutableLiveData<List<CatEntity>>()

    private val _fragmentState =
        MutableStateFlow<CatsFavoriteListFragmentState>(CatsFavoriteListFragmentState.ShowShimmers)

    init {
        viewModelScope.launch { loadScreenData() }
    }

    fun isFavoriteClicked(catId: String?, imageUrl: String?) {
        viewModelScope.launch {
            removeCat.execute(
                catId = catId,
                imageUrl = imageUrl
            )
            reloadScreenData()
        }
    }

    private fun reloadScreenData() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getAllFavoriteCats.execute()) {
                else -> {
                    cats.postValue(result.cats.toEntity().reversed())
                }
            }
        }
    }

    private fun loadScreenData() {
        updateState(CatsFavoriteListFragmentState.ShowShimmers)
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getAllFavoriteCats.execute()) {
                else -> {
                    cats.postValue(result.cats.toEntity().reversed())
                    updateState(CatsFavoriteListFragmentState.ShowContent)
                }
            }
        }
    }

    private fun updateState(state: CatsFavoriteListFragmentState) {
        viewModelScope.launch {
            _fragmentState.emit(state)
        }
    }

    companion object {
        fun getFactory(
            removeCat: RemoveCat,
            getAllFavoriteCats: GetAllFavoriteCats
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CatsFavoriteListViewModel(
                        removeCat = removeCat,
                        getAllFavoriteCats = getAllFavoriteCats
                    ) as T
                }
            }
        }
    }

}