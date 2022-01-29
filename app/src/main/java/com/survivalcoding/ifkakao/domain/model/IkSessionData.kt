package com.survivalcoding.ifkakao.domain.model

data class IkSessionData(
    val company: String,
    val content: String,
    val exposureDay: Int,
    val field: String,
    val id: Int,
    val isSpotlight: Boolean,
    val hasVideo: Boolean,
    val hashTag: String,
    val sessionSpeakers: List<IkSessionSpeaker>,
    val tag: List<IkTagInfo>,
    val title: String,
    val video: IkSessionVideo,
) {
    companion object {
        fun getEmptySessionData(): IkSessionData {
            return IkSessionData(
                company = "",
                content = "",
                exposureDay = -1,
                field = "",
                id = -1,
                isSpotlight = false,
                hasVideo = false,
                hashTag = "",
                sessionSpeakers = listOf(),
                tag = listOf(),
                title = "",
                video = IkSessionVideo.getEmptyVideo()
            )
        }
    }
}