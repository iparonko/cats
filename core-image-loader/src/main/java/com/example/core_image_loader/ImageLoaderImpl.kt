package com.example.core_image_loader

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageLoaderImpl : ImageLoader {

    override fun load(
        imageView: ImageView,
        url: String,
        placeHolder: Int?
    ) {
        if (url.isBlank()) {
            return
        }
        Picasso
            .get()
            .load(url)
            .apply {
                if (placeHolder != null) {
                    placeholder(placeHolder)
                }
            }.into(imageView)
    }

}
