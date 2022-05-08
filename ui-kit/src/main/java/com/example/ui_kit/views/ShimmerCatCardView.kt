package com.example.ui_kit.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.ui_kit.R
import com.example.ui_kit.databinding.ShimmerItemCatListBinding
import com.example.ui_kit.utils.inflateView

class ShimmerCatCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val binding by lazy { ShimmerItemCatListBinding.bind(getChildAt(0)) }

    init {
        inflateView(R.layout.shimmer_item_cat_list, true)
    }

}
