package com.survivalcoding.ifkakao.domain.model

sealed class IkContent()

class IkListItem(
    val id: Int,
    val images: List<IkPCImage>,
    val videos: List<IkVideo>,
    val company: String,
    val field: String,
    val title: String,
    val isSpotlight: Boolean,
    val exposureDay: List<String>,
) : IkContent()

class IkMainItem(
    val id: Int,
    val images: List<IkPCImage>,
    val videos: List<IkVideo>,
    private val field: String,
    private val company: String,
    private val classification: List<String>,
    private val techClassification: List<String>,
    val title: String,
    val content: String,
    private val contentTag: String,
    val speakerProfiles: List<IkSpeakerProfile>,
    val speakerList: List<IkSpeaker>,
) : IkContent() {
    val keyword = listOf(field, company).plus(classification).plus(techClassification)
    val hashtag = contentTag.split("  ")
}