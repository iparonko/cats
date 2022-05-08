package com.example.cats_list_impl.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cats_favorites_common.domain.interactors.RemoveCat
import com.example.cats_favorites_common.domain.interactors.SaveCat
import com.example.cats_list_impl.domain.interactors.GetImages
import com.example.cats_list_impl.domain.models.CatEntity
import com.example.cats_list_impl.presentation.state.CatsListFragmentState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatsListViewModel(
    private val getImages: GetImages,
    private val saveCat: SaveCat,
    private val removeCat: RemoveCat
) : ViewModel() {

    val fragmentState: StateFlow<CatsListFragmentState>
        get() = _fragmentState

    val cats = MutableLiveData<List<CatEntity>>()

    var isLoading = false

    private val _fragmentState =
        MutableStateFlow<CatsListFragmentState>(CatsListFragmentState.ShowShimmers)

    private var CURRENT_PAGE_NUMBER_FOR_LOAD = 0

    init {
        viewModelScope.launch { loadScreenData() }
    }

    fun loadMoreItems() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            when (val result = getImages.execute(
                page = CURRENT_PAGE_NUMBER_FOR_LOAD
            )) {
                is GetImages.Result.Success -> {
                    cats.postValue(concatCatsAfterLoad(result.contentEntity.cats))
                    CURRENT_PAGE_NUMBER_FOR_LOAD++
                    isLoading = false
                }
                is GetImages.Result.Error -> updateState(
                    CatsListFragmentState.ShowError(
                        networkError = result.networkError
                    )
                )
            }
        }
    }

    fun isFavoriteClicked(catId: String?, imageUrl: String?, state: Boolean) {
        viewModelScope.launch {
            if (state) {
                saveCat.execute(
                    catId = catId,
                    imageUrl = imageUrl
                )
            } else {
                removeCat.execute(
                    catId = catId,
                    imageUrl = imageUrl
                )
            }
        }
    }

    private fun concatCatsAfterLoad(catsAfterLoad: List<CatEntity>): List<CatEntity> {
        val cats = mutableListOf<CatEntity>()
        this.cats.value?.let { cats.addAll(it) }
        cats.addAll(catsAfterLoad)
        return cats
    }

    private fun loadScreenData() {
        updateState(CatsListFragmentState.ShowShimmers)
        viewModelScope.launch(Dispatchers.IO) {
            isLoading = true
            when (val result = getImages.execute(
                page = CURRENT_PAGE_NUMBER_FOR_LOAD
            )) {
                is GetImages.Result.Success -> {
                    cats.postValue(result.contentEntity.cats)
                    updateState(CatsListFragmentState.ShowContent)
                    CURRENT_PAGE_NUMBER_FOR_LOAD++
                    isLoading = false
                }
                is GetImages.Result.Error -> updateState(
                    CatsListFragmentState.ShowError(
                        networkError = result.networkError
                    )
                )
            }
        }
    }

    private fun updateState(state: CatsListFragmentState) {
        viewModelScope.launch {
            _fragmentState.emit(state)
        }
    }

    companion object {
        fun getFactory(
            getImages: GetImages,
            saveCat: SaveCat,
            removeCat: RemoveCat
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CatsListViewModel(
                        getImages = getImages,
                        saveCat = saveCat,
                        removeCat = removeCat
                    ) as T
                }
            }
        }
    }

}