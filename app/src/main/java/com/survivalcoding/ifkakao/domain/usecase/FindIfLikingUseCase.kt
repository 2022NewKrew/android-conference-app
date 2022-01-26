package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class FindIfLikingUseCase(private val repository: SessionLocalRepository) {
    suspend operator fun invoke(idx: Int): Boolean = repository.isLiking(idx)
}