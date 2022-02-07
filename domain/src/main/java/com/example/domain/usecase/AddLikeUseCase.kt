package com.example.domain.usecase

import com.example.domain.repository.LikeRepository
import javax.inject.Inject

class AddLikeUseCase @Inject constructor(private val likeRepository: LikeRepository) {
    suspend operator fun invoke(id: String, idx: Int) {
        likeRepository.addLike(id, idx)
    }
}