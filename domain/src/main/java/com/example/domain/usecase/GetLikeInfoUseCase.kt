package com.example.domain.usecase

import com.example.domain.entity.Like
import com.example.domain.repository.LikeRepository
import javax.inject.Inject

class GetLikeInfoUseCase @Inject constructor(private val likeRepository: LikeRepository) {
    suspend operator fun invoke(id: String): List<Int>? = likeRepository.getAll(id)
}