package com.survivalcoding.ifkakao.testUtil

import com.survivalcoding.ifkakao.domain.entity.*

class MockIfKakaoSessionsBuilder {

    private var index = 1
    private val mockSessions = mutableListOf<Session>()

    fun build() = mockSessions

    fun addSession(
        company: String = "카카오",
        content: String = "",
        contentTag: String = "",
        title: String = "",
        thumbnailUrl: String = "",
        files: List<File> = listOf(),
        videos: List<Video> = listOf(),
        categories: Categories = Categories(),
        contentsSpeakers: List<Speaker> = listOf(),
        isHighlight: Boolean = false,
        isFavorite: Boolean = false,
        exposureDay: Int = 3
    ): MockIfKakaoSessionsBuilder {
        mockSessions.add(
            Session(
                idx = index++,
                company = company,
                content = content,
                contentTag = contentTag,
                title = title,
                thumbnailUrl = thumbnailUrl,
                files = files,
                videos = videos,
                categories = categories,
                contentsSpeakers = contentsSpeakers,
                isHighlight = isHighlight,
                isFavorite = isFavorite,
                exposureDay = exposureDay
            )
        )

        return this
    }

    fun shuffle(): MockIfKakaoSessionsBuilder {
        mockSessions.shuffle()

        return this
    }
}
