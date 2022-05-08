package com.example.cats_favorites_impl.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.cats_favorites_impl.domain.models.CatEntity
import com.example.ui_kit.views.CatCardView
import com.example.ui_kit.R as uiKitR

internal class CatFavoriteListAdapter :
    androidx.recyclerview.widget.ListAdapter<CatEntity, CatFavoriteListViewHolder>(CATS_COMPARATOR) {

    var callbackFavoriteListAdapter: CallbackFavoriteListAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatFavoriteListViewHolder {
        return CatFavoriteListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                uiKitR.layout.layout_cat_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatFavoriteListViewHolder, position: Int) {
        (holder.itemView as? CatCardView)?.layout(0, 0, 0, 0)
        holder.callbackCatListViewHolder =
            object : CatFavoriteListViewHolder.CallbackCatListViewHolder {
                override fun onFavoriteIconClicked(
                    catId: String?,
                    imageUrl: String?
                ) {
                    callbackFavoriteListAdapter?.onFavoriteIconClicked(
                        catId = catId,
                        imageUrl = imageUrl
                    )
                }
            }
        holder.bind(getItem(position))
    }

    interface CallbackFavoriteListAdapter {
        fun onFavoriteIconClicked(catId: String?, imageUrl: String?)
    }

    companion object {

        private val CATS_COMPARATOR = object : DiffUtil.ItemCallback<CatEntity>() {
            override fun areItemsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CatEntity, newItem: CatEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}