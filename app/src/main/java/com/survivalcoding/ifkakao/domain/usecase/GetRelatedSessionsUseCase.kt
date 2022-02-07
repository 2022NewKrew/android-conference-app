package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import javax.inject.Inject

class GetRelatedSessionsUseCase @Inject constructor(
    private val content: IfKakaoContent,
) {
    operator fun invoke(session: IkSessionData) = content.data.filter {
        it.field == session.field && it.id != session.id
    }
}