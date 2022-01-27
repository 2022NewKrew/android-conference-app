package com.survivalcoding.ifkakao.domain.model

data class SessionItem(
    val idx: Int,
    val title: String,
    val content: String,
    val company: String,
    val field: String,
    val contentTag: List<String>,
    val videoUrl: String, // from LinkList.video.url
    val videoDuration: String, // from LinkList.video.description
    val mainImageUrl: String, // from LinkList.mo_main_image
    val imageUrl: String, // from LinkList.mo_image
    val classifications: List<String>, // from RelationList.classification
    val techClassifications: List<String>, // from RelationList.tech_classification
    val mainExposureDay: Int, // from relationList.main_exposure_day
    val speakerProfiles: List<SpeakerProfileItem>, // from LinkList.SpeakerProfile & contentSpeakerList
    val isHighlight: Boolean
)