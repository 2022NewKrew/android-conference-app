package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IfKakaoContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetSessionsByDayUseCase @Inject constructor(
    private val content: IfKakaoContent,
) {
    operator fun invoke(day: Int) = flow {
        val list = content.data.filter {
            when (day) {
                1 -> it.exposureDay == 1
                2 -> it.exposureDay == 2
                else -> {
                    true
                }
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