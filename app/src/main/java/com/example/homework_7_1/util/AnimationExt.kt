package com.example.homework_7_1.util

import android.view.View
import android.view.animation.AlphaAnimation

fun View.visible() {
    val animation = AlphaAnimation(0f, 1f)
    animation.duration = 300
    this.startAnimation(animation)
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}