package com.example.domain.entity

data class LinkList(
    val file: List<String>,
    val image: List<String>,
    val webUrl: List<String>,
    val video: List<Video>,
    val pcThumbnail: List<String>,
    val moThumbnail: List<String>,
    val talkThumbnail: List<String>,
    val speakerProfile: List<SpeakerProfile>,
    val pcMainImage: List<PcMainImage>,
    val moMainImage: List<MoMainImage>,
    val pcImage: List<PcImage>,
    val moImage: List<MoImage>,
    val shareImage: List<ShareImage>,
    val pcSpotlight: List<PcSpotlight>,
    val moSpotlight: List<MoSpotlight>
)