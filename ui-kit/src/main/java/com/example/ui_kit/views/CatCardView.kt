package com.example.ui_kit.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import com.example.core_image_loader.ImageUtils
import com.example.core_os.saveMediaToStorage
import com.example.ui_kit.R
import com.example.ui_kit.databinding.ItemCatListBinding
import com.example.ui_kit.utils.inflateView

class CatCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var callbackCatCardView: CallbackCatCardView? = null

    var catId: String? = null
        set(value) {
            if (field != value) {
                field = value
            }
        }

    var imageUrl: String? = null
        set(value) {
            if (field != value) {
                field = value
                loadImage()
            }
        }

    var isFavorite: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                updateFavoriteState()
            }
        }

    private val binding by lazy { ItemCatListBinding.bind(getChildAt(0)) }

    init {
        inflateView(R.layout.item_cat_list, true)
        binding.downloadIconView.setOnClickListener {
            saveMediaToStorage(
                fileName = catId,
                imageView = this.binding.imageView,
                context = context
            )
        }
        binding.starIconView.setOnClickListener {
            callbackCatCardView?.onFavoriteIconClicked(
                catId = catId,
                imageUrl = imageUrl,
                state = isFavorite
            )
        }
    }

    private fun loadImage() {
        ImageUtils.getImageLoader().load(
            imageView = binding.imageView,
            url = imageUrl ?: TODO("Handler empty url"),
            placeHolder = R.drawable.shimmer_item_cats_list_background
        )
    }

    private fun updateFavoriteState() {
        if (isFavorite) {
            binding.starIconView.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_baseline_star_rate_32_red
                )
            )
        } else {
            binding.starIconView.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_baseline_star_border_32
                )
            )
        }
    }

    interface CallbackCatCardView {
        fun onFavoriteIconClicked(catId: String?, imageUrl: String?, state: Boolean)
    }

}
