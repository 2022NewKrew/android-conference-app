package com.survivalcoding.ifkakao.domain.model

data class IkTagInfo(
    val tagType: TagType,
    val text: String,
    val id: Int = -1,
) {
    companion object {
        fun getEmptyTagInfo(): IkTagInfo {
            return IkTagInfo(
                tagType = TagType.COMPANY,
                text = "카카오",
                id = -1,
            )
        }
    }
}

enum class TagType {
    COMPANY, FIELD, CLASSIFICATION, TECH_CLASSIFICATION;
}