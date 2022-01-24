package com.survivalcoding.ifkakao.domain.model

data class IkSpeaker(
    private val nameKor: String,
    private val nameEng: String,
    val company: String,
    val occupation: String,
) {
    val name = "$nameKor $nameEng"
}