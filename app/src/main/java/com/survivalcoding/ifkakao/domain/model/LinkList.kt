package com.survivalcoding.ifkakao.domain.model

data class LinkList(
    val file: List<File>,
    val pcImage: List<File>,
    val speakerProfile: List<File>,
    val video: List<File>
)