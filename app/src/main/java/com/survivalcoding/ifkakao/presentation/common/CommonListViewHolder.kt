package com.survivalcoding.ifkakao.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.survivalcoding.ifkakao.databinding.CommonListBinding

class CommonListViewHolder private constructor(private val binding: CommonListBinding) :
    CommonViewHolder(binding.root) {
    override fun bind(binder: CommonBinder) {
        binder as CommonListBinder
        (binding.recyclerView.adapter as CommonAdapter).submitList(binder.commonBinderList) {
            binding.recyclerView.scrollToPosition(0)
        }
    }

    companion object {
        fun from(parent: ViewGroup): CommonListViewHolder {
            return CommonListViewHolder(
                CommonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                    .apply {
                        this.recyclerView.adapter = CommonAdapter()
                        this.recyclerView.addItemDecoration(StickyFooterItemDecoration())
                    }
            )
        }
    }
}