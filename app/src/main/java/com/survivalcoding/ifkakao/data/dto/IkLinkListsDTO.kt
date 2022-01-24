package com.survivalcoding.ifkakao.data.dto

data class IkLinkListsDTO(
    val FILE: List<IkInfoDTO>? = null,
    val IMAGE: List<IkInfoDTO>? = null,
    val MO_IMAGE: List<IkInfoDTO>? = null,
    val MO_MAIN_IMAGE: List<IkInfoDTO>? = null,
    val MO_SPOTLIGHT: List<IkInfoDTO>? = null,
    val MO_THUMBNAIL: List<IkInfoDTO>? = null,
    val PC_IMAGE: List<IkInfoDTO>? = null,
    val PC_MAIN_IMAGE: List<IkInfoDTO>? = null,
    val PC_SPOTLIGHT: List<IkInfoDTO>? = null,
    val PC_THUMBNAIL: List<IkInfoDTO>? = null,
    val SHARE_IMAGE: List<IkInfoDTO>? = null,
    val SPEAKER_PROFILE: List<IkInfoDTO>? = null,
    val TALK_THUMBNAIL: List<IkInfoDTO>? = null,
    val VIDEO: List<IkInfoDTO>? = null,
    val WEB_URL: List<IkInfoDTO>? = null,
)