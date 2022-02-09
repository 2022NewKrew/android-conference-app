package com.survivalcoding.ifkakao.presentation.common

import com.survivalcoding.ifkakao.R

class SessionFileBinder(
    val fileUri: String,
    val description: String,
    val onDownloadClick: (downloadUri: String, description: String) -> Unit
) : CommonBinder {
    override val id = ID

    override fun areContentsTheSame(oldItem: CommonBinder, newItem: CommonBinder): Boolean {
        oldItem as SessionFileBinder
        newItem as SessionFileBinder

        return oldItem.fileUri == newItem.fileUri && oldItem.description == newItem.description
    }

    companion object {
        const val ID = R.layout.session_file
    }
}