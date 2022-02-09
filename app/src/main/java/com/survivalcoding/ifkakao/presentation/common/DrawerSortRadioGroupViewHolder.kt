package com.survivalcoding.ifkakao.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.survivalcoding.ifkakao.databinding.DrawerSortRadioGroupBinding

class DrawerSortRadioGroupViewHolder private constructor(private val binding: DrawerSortRadioGroupBinding) :
    CommonViewHolder(binding.root) {

    override fun bind(binder: CommonBinder) {
        binder as DrawerSortRadioGroupBinder

        binding.sortRadioGroup.check(binder.checkedId)
        binding.sortRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            binder.checkedId = checkedId
            binder.checkedChangeListener.onCheckedChanged(
                group,
                checkedId
            )
        }
    }

    companion object {
        fun from(parent: ViewGroup): DrawerSortRadioGroupViewHolder {
            return DrawerSortRadioGroupViewHolder(
                DrawerSortRadioGroupBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}