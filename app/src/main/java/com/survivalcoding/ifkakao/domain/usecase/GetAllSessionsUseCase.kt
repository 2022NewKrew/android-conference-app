package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllSessionsUseCase @Inject constructor(
    private val content: IfKakaoContent,
) {
    operator fun invoke() = flow {
        val list = content.data
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