package com.example.domain.usecase

import com.example.domain.entity.Like
import com.example.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLikeInfoUseCase @Inject constructor(private val likeRepository: LikeRepository) {
    operator fun invoke(id: String): Flow<List<Int>> = likeRepository.getAll(id)
}