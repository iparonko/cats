package com.example.utils

import androidx.fragment.app.FragmentTransaction
import com.example.ui_kit.R as uiKitR

fun FragmentTransaction.setSlideRightToLeftAnimation(): FragmentTransaction {
    this.setCustomAnimations(
        uiKitR.anim.slide_left_in,
        uiKitR.anim.slide_left_out,
        uiKitR.anim.slide_right_in,
        uiKitR.anim.slide_right_out
    )
    return this
}
