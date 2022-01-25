package com.survivalcoding.ifkakao.domain.model

data class IkTagInfo(
    val tagType: TagType,
    val text: String,
    val id: Int,
)

enum class TagType {
    COMPANY, FIELD, CLASSIFICATION, TECH_CLASSIFICATION;
}