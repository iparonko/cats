package com.example.ui_kit.recyclerview

import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener(@NonNull private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    abstract val isLoading: Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = layoutManager.itemCount
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        if (!isLoading && lastVisibleItemPosition == totalItemCount - 1) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()

}