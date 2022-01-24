package com.survivalcoding.ifkakao.domain.model

import com.survivalcoding.ifkakao.data.dto.IkDataDTO

sealed class IkContent()

class IkListItem(
    val imageUrl: String,
    val videoLength: String,
    val company: String,
    val field: String,
    val title: String,
    val isSpotlight: Boolean,
    private val exposureDay: String,
) : IkContent() {
    val day = when (exposureDay) {
        "1Day" -> 1
        "2Day" -> 2
        else -> 3
    }
}

class IkMainItem(
    val imageUrl: String,
    val videoLength: String,
    private val field: String,
    private val company: String,
    private val classification: List<String>,
    private val techClassification: List<String>,
    val title: String,
    val content: String,
    private val contentTag: String,
    val speakerImageUrl: String,
    val speaker: IkSpeaker,
) : IkContent() {
    val keyword = listOf(field, company).plus(classification).plus(techClassification)
    val hashtag = contentTag.split("  ")
}