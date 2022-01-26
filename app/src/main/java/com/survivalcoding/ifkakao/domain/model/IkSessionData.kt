package com.survivalcoding.ifkakao.domain.model

data class IkSessionData(
    val company: String,
    val content: String,
    val exposureDay: Int,
    val field: String,
    val id: Int,
    val isSpotlight: Boolean,
    val isVideo: Boolean,
    val hashTag: String,
    val sessionSpeakers: List<IkSessionSpeaker>,
    val tag: List<IkTagInfo>,
    val title: String,
    val video: IkSessionVideo,
)
