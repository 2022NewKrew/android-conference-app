package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.domain.model.IkComment
import com.survivalcoding.ifkakao.domain.repository.LocalRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class InsertNewCommentUseCase @Inject constructor(
    private val localRepository: LocalRepository,
) {
    suspend operator fun invoke(id: Int, comment: IkComment) {
        val localSession = localRepository.getSessionLocalDataById(id).first()
        val comments = localSession.comments
        val newComments = listOf(comment) + comments
        localRepository.insertSessionLocalData(localSession.copy(comments = newComments))
    }
}