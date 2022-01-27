package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.presentation.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSessionsByTagUseCase @Inject constructor(
    private val content: IfKakaoContent,
) {
    operator fun invoke(predicate: (IkSessionData) -> Boolean) = flow<UiState> {
        val list = content.data.filter(predicate)
        if (list.isNotEmpty()) {
            emit(UiState.Success(list))
            return@flow
        } else {
            throw Exception("empty list after filtering")
        }
    }
        .catch { emit(UiState.Error(it)) }
        .flowOn(Dispatchers.IO)
}