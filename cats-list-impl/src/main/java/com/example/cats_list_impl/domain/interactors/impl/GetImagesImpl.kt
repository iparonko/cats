package com.example.cats_list_impl.domain.interactors.impl

import com.example.cats_favorites_common.domain.interactors.GetAllFavoriteCats
import com.example.cats_list_impl.data.models.CatDTO
import com.example.cats_list_impl.data.repositories.ImagesSearchApiInterface
import com.example.cats_list_impl.domain.interactors.GetImages
import com.example.cats_list_impl.domain.models.CatEntity
import com.example.cats_list_impl.domain.models.ContentEntity
import com.example.cats_list_impl.types.OrderType
import com.example.core_data.retrofit.coroutines.ApiResult

class GetImagesImpl(
    private val imagesSearchApiInterface: ImagesSearchApiInterface,
    private val getAllFavoriteCats: GetAllFavoriteCats
) : GetImages {

    override suspend fun execute(page: Int?): GetImages.Result {
        val allFavoriteCats = when (val result = getAllFavoriteCats.execute()) {
            else -> result.cats
        }
        return when (val result = imagesSearchApiInterface.getImagesSearch(
            limit = DEFAULT_LIMIT,
            page = page,
            orderType = DEFAULT_ORDER.value
        ).await()) {
            is ApiResult.Success -> GetImages.Result.Success(
                contentEntity = mapContent(
                    favoriteCats = allFavoriteCats,
                    catsListFromInternet = result.value
                )
            )
            is ApiResult.Error -> GetImages.Result.Error(
                networkError = result.networkError
            )
        }
    }

    private fun mapContent(
        favoriteCats: List<com.example.cats_favorites_common.domain.models.CatEntity>,
        catsListFromInternet: List<CatDTO>
    ): ContentEntity {
        val resultList = mutableListOf<CatEntity>()
        catsListFromInternet.forEach { catFromInternet ->
            val cat = CatEntity(
                id = catFromInternet.id,
                url = catFromInternet.url
            )
            favoriteCats.forEach { favoriteCat ->
                if (catFromInternet.id == favoriteCat.id) {
                    cat.isFavorite = true
                }
            }
            resultList.add(cat)
        }
        return ContentEntity(cats = resultList)
    }

    companion object {
        const val DEFAULT_LIMIT = 10
        val DEFAULT_ORDER = OrderType.DESC
    }
}