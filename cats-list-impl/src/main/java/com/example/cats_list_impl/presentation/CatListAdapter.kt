package com.example.cats_list_impl.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cats_list_impl.domain.models.CatEntity
import com.example.ui_kit.views.CatCardView
import com.example.ui_kit.R as uiKitR

internal class CatListAdapter : ListAdapter<CatEntity, CatListViewHolder>(CATS_COMPARATOR) {

    var callbackCatListAdapter: CallbackCatListAdapter? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListViewHolder {
        return CatListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                uiKitR.layout.layout_cat_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CatListViewHolder, position: Int) {
        (holder.itemView as? CatCardView)?.layout(0, 0, 0, 0)
        holder.callbackCatListViewHolder = object : CatListViewHolder.CallbackCatListViewHolder {
            override fun onFavoriteIconClicked(catId: String?, imageUrl: String?, state: Boolean) {
                callbackCatListAdapter?.onFavoriteIconClicked(
                    catId = catId,
                    imageUrl = imageUrl,
                    state = state
                )
            }
        }
        holder.bind(getItem(position))
    }

    interface CallbackCatListAdapter {
        fun onFavoriteIconClicked(catId: String?, imageUrl: String?, state: Boolean)
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