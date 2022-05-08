package com.example.core_image_loader

import android.widget.ImageView
import com.squareup.picasso.Callback

interface ImageLoader {

    fun load(
        imageView: ImageView,
        url: String,
        placeHolder: Int? = null
    )
}
