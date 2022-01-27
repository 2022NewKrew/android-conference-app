package com.survivalcoding.ifkakao.data.vo

data class LinkListVo(
    val FILE: List<FileVo>,
    val IMAGE: List<Any>,
    val MO_IMAGE: List<MoImageVo>,
    val MO_MAIN_IMAGE: List<MoMainImageVo>,
    val MO_SPOTLIGHT: List<MoSpotlightVo>,
    val SHARE_IMAGE: List<ShareImageVo>,
    val SPEAKER_PROFILE: List<SpeakerProfileVo>,
    val TALK_THUMBNAIL: List<Any>,
    val VIDEO: List<VideoVo>,
    val WEB_URL: List<Any>
)