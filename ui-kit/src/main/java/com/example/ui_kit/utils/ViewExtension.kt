package com.example.ui_kit.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateView(@LayoutRes resId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(resId, this, attachToRoot)
}