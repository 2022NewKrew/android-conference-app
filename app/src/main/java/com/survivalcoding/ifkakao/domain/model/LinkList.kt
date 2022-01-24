package com.survivalcoding.ifkakao.domain.model

data class LinkList(
    val FILE: List<FILE>,
    val IMAGE: List<Any>,
    val MO_THUMBNAIL: List<Any>,
    val PC_IMAGE: List<PCIMAGE>,
    val PC_THUMBNAIL: List<Any>,
    val SHARE_IMAGE: List<SHAREIMAGE>,
    val SPEAKER_PROFILE: List<SPEAKERPROFILE>,
    val TALK_THUMBNAIL: List<Any>,
    val VIDEO: List<VIDEO>,
    val WEB_URL: List<Any>
)