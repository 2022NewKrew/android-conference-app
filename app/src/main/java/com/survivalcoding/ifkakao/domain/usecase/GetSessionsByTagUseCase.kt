package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.model.IkTagInfo
import com.survivalcoding.ifkakao.domain.model.TagType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSessionsByTagUseCase @Inject constructor(
    private val content: IfKakaoContent,
) {
    operator fun invoke(tag: IkTagInfo) = flow {
        val list = content.data.filter {
            when (tag.tagType) {
                TagType.COMPANY -> it.company == tag.text
                TagType.FIELD -> it.field == tag.text
                TagType.CLASSIFICATION -> it.tag.any { tag -> tag.text == tag.text }
                TagType.TECH_CLASSIFICATION -> it.tag.any { tag -> tag.text == tag.text }
            }
        }
        if (list.isNotEmpty()) {
            emit(list)
            return@flow
        } else {
            throw Exception("empty list after filtering")
        }
    }
        .catch { emit(listOf()) }
        .flowOn(Dispatchers.IO)
}