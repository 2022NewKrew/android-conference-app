package com.survivalcoding.ifkakao.presentation.common

import android.text.Editable
import com.survivalcoding.ifkakao.R

class DrawerEditTextBinder(
    var text: String,
    val afterTextChanged: (Editable) -> Unit
) : CommonBinder {
    override val id = ID

    override fun areContentsTheSame(oldItem: CommonBinder, newItem: CommonBinder): Boolean {
        oldItem as DrawerEditTextBinder
        newItem as DrawerEditTextBinder

        return oldItem.text == newItem.text && oldItem.afterTextChanged == newItem.afterTextChanged
    }

    companion object {
        const val ID = R.layout.drawer_edittext
    }
}