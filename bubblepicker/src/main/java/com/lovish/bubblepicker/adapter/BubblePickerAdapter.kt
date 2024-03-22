package com.lovish.bubblepicker.adapter

import com.lovish.bubblepicker.model.PickerItem

/**
 * Created by lovish on 22/03/24.
 */
interface BubblePickerAdapter {
    val totalCount: Int
    fun getItem(position: Int): PickerItem
}