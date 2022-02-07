package com.example.domain.entity

import com.google.gson.annotations.SerializedName

data class LinkList(
    val file: List<String>?,
    val image: List<String>?,
    val webUrl: List<String>?,
    @SerializedName("VIDEO") val video: List<Video>?,
    val pcThumbnail: List<String>?,
    val moThumbnail: List<String>?,
    val talkThumbnail: List<String>?,
    @SerializedName("SPEAKER_PROFILE") val speakerProfile: List<SpeakerProfile>?,
    @SerializedName("PC_MAIN_IMAGE") val pcMainImage: List<PcMainImage>?,
    @SerializedName("MO_MAIN_IMAGE") val moMainImage: List<MoMainImage>?,
    @SerializedName("PC_IMAGE") val pcImage: List<PcImage>?,
    @SerializedName("MO_IMAGE") val moImage: List<MoImage>?,
    @SerializedName("SHARE_IMAGE") val shareImage: List<ShareImage>?,
    @SerializedName("PC_SPOTLIGHT") val pcSpotlight: List<PcSpotlight>?,
    @SerializedName("MO_SPOTLIGHT") val moSpotlight: List<MoSpotlight>?
)