package com.survivalcoding.ifkakao.presentation.common

import android.widget.RadioGroup
import com.survivalcoding.ifkakao.R

class DrawerSortRadioGroupBinder(
    val checkedChangeListener: RadioGroup.OnCheckedChangeListener,
    var checkedId: Int
) : CommonBinder {
    override val id = ID

    override fun areContentsTheSame(oldItem: CommonBinder, newItem: CommonBinder): Boolean {
        oldItem as DrawerSortRadioGroupBinder
        newItem as DrawerSortRadioGroupBinder

        return oldItem.checkedChangeListener == newItem.checkedChangeListener && oldItem.checkedId == newItem.checkedId
    }

    companion object {
        const val ID = R.layout.drawer_sort_radio_group
    }
}