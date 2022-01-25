package com.survivalcoding.ifkakao.domain.model

data class IkSessionData(
    val company: String,
    val content: String,
    val field: String,
    val id: Int,
    val sessionTag: List<String>,
    val sessionSpeakers: List<IkSessionSpeaker>,
    val title: String,
    val video: IkSessionVideo,
    val isSpotlight: Boolean,
    val isVideo: Boolean,
)