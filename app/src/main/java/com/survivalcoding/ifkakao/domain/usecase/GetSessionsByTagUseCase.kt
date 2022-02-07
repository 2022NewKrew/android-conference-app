package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.model.IkTagInfo
import javax.inject.Inject

class GetSessionsByTagUseCase @Inject constructor(
    private val content: IfKakaoContent,
) {
    operator fun invoke(tag: IkTagInfo) = content.data.filter {
        it.tag.any { it.text == tag.text }
    }
}