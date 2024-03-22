package com.lovish.bubblepicker.model

import android.graphics.Typeface
import android.graphics.drawable.Drawable

/**
 * Created by lovish on 22/03/24.
 */

data class PickerItem @JvmOverloads constructor(
    var title: String? = null,
    var icon: Drawable? = null,
    var iconOnTop: Boolean = true,
    var color: Int? = null,
    var gradient: BubbleGradient? = null,
    var overlayAlpha: Float = 0.5f,
    var typeface: Typeface = Typeface.DEFAULT,
    var textColor: Int? = null,
    var textSize: Float = 40f,
    var backgroundImage: Drawable? = null,
    var isSelected: Boolean = false,
    var customData: Any? = null
)
