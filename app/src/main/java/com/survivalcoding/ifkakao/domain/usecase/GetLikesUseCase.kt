package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class GetLikesUseCase(private val repository: SessionLocalRepository) {
    suspend operator fun invoke(): List<Like> = repository.getLikes().sortedBy { it.idx }
}