package com.lovish.bubblepicker

import com.lovish.bubblepicker.model.PickerItem

/**
 * Created by lovish on 22/03/24.
 */
interface BubblePickerListener {
    fun onBubbleSelected(item: PickerItem)
    fun onBubbleDeselected(item: PickerItem)
}