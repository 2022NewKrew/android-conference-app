package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository

class DeleteLikeUseCase(private val repository: SessionLocalRepository) {
    suspend operator fun invoke(idx: Int) = repository.deleteLike(Like(idx = idx))
}