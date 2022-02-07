package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class AddLikeUseCase(private val repository: SessionLocalRepository) {
    suspend operator fun invoke(idx: Int) = repository.addLike(Like(idx = idx))
}