package com.wh2soft.tddexample.shared

import android.graphics.drawable.Drawable
import top.defaults.drawabletoolbox.DrawableBuilder

interface DrawableFactory {
    fun build(): Drawable
}

const val COLOR_DEFAULT = 0xFF008577.toInt()
const val COLOR_DEFAULT_DARK = 0xFF004740.toInt()
const val COLOR_PRESSED = 0xFF01C1AE.toInt()

object Drawable {

    fun taskDetailsTitleDrawableFactory(): DrawableFactory = object : DrawableFactory {
        override fun build(): Drawable {
            return DrawableBuilder()
                    .rectangle()
                    .cornerRadius(10)
                    .hairlineBordered()
                    .strokeColor(COLOR_DEFAULT_DARK)
                    .strokeColorPressed(COLOR_PRESSED)
                    .ripple()
                    .rippleColor(COLOR_PRESSED)
                    .build()
        }
    }
}