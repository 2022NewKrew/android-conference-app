package com.survivalcoding.ifkakao.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.survivalcoding.ifkakao.databinding.DrawerEdittextBinding

class DrawerEditTextViewHolder private constructor(private val binding: DrawerEdittextBinding) :
    CommonViewHolder(binding.root) {

    override fun bind(binder: CommonBinder) {
        binder as DrawerEditTextBinder

        binding.editText.doAfterTextChanged { }
        binding.editText.setText(binder.text)
        binding.editText.doAfterTextChanged { text ->
            binder.text = text.toString()
            binder.afterTextChanged(
                text ?: return@doAfterTextChanged
            )
        }
    }

    companion object {
        fun from(parent: ViewGroup): DrawerEditTextViewHolder {
            return DrawerEditTextViewHolder(
                DrawerEdittextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}