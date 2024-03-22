package com.lovish.bubblepicker.model

import android.graphics.Color

/**
 * Created by lovish on 22/03/24.
 */
data class Color(var color: Int) {

    val red: Float
        get() = Color.red(color) / 256f

    val green: Float
        get() = Color.green(color) / 256f

    val blue: Float
        get() = Color.blue(color) / 256f

    val alpha: Float
        get() = Color.alpha(color) / 256f

}