package com.survivalcoding.ifkakao.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.survivalcoding.ifkakao.databinding.SessionFileBinding

class SessionFileViewHolder private constructor(private val binding: SessionFileBinding) :
    CommonViewHolder(binding.root) {

    override fun bind(binder: CommonBinder) {
        binder as SessionFileBinder

        binding.fileDescriptionTextView.text = binder.description
        binding.fileDescriptionTextView.setOnClickListener {
            binder.onDownloadClick(binder.fileUri, binder.description)
        }
    }

    companion object {
        fun from(parent: ViewGroup): SessionFileViewHolder {
            return SessionFileViewHolder(
                SessionFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }
}