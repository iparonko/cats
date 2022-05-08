package com.example.cats_list_impl.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cats_list_impl.domain.models.CatEntity
import com.example.ui_kit.views.CatCardView


internal class CatListViewHolder(
    item: View
) : RecyclerView.ViewHolder(item) {

    var callbackCatListViewHolder: CallbackCatListViewHolder? = null

    private var catEntity: CatEntity? = null

    fun bind(catEntity: CatEntity) {
        this.catEntity = catEntity
        catEntity.let {
            (itemView as? CatCardView)?.run {
                catId = it.id
                isFavorite = catEntity.isFavorite
                imageUrl = it.url
                callbackCatCardView = object : CatCardView.CallbackCatCardView {
                    override fun onFavoriteIconClicked(catId: String?, imageUrl: String?, state: Boolean) {
                        isFavorite = state.not()
                        callbackCatListViewHolder?.onFavoriteIconClicked(
                            catId = catId,
                            imageUrl = imageUrl,
                            state.not()
                        )
                    }
                }
            }
        }
    }

    interface CallbackCatListViewHolder {
        fun onFavoriteIconClicked(catId: String?, imageUrl: String?, state: Boolean)
    }

}