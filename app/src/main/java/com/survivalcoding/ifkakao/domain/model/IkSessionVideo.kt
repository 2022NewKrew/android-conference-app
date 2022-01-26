package com.survivalcoding.ifkakao.domain.model

data class IkSessionVideo(
    val videoLength: String?,
    val videoUrl: String,
    val thumbnailUrl: String,
) {
    companion object {
        fun getEmptyVideo(): IkSessionVideo {
            return IkSessionVideo("", "", "")
        }
    }
}