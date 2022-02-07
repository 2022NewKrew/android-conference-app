package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.repository.LocalRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ToggleLikedSessionUseCase @Inject constructor(
    private val localRepository: LocalRepository,
) {
    suspend operator fun invoke(id: Int) {
        val localSession = localRepository.getSessionLocalDataById(id).first()
        localRepository.insertSessionLocalData(localSession.copy(isLiked = !localSession.isLiked))
    }
}