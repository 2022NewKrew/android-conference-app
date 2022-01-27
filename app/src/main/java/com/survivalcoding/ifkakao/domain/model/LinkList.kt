package com.survivalcoding.ifkakao.domain.model

data class LinkList(
    val FILE: List<File>,
    val PC_IMAGE: List<File>,
    val SPEAKER_PROFILE: List<File>,
    val VIDEO: List<File>
)