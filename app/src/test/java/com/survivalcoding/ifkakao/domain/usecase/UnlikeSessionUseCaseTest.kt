package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.like.LikeInMemoryDataSource
import com.survivalcoding.ifkakao.data.datasource.session.local.SessionLocalDataSource
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class UnlikeSessionUseCaseTest {

    private lateinit var useCase: UnlikeSessionUseCase
    private lateinit var repository: SessionRepository

    @Before
    fun setUp() {
        repository = SessionRepositoryImpl(SessionLocalDataSource(), LikeInMemoryDataSource())
        useCase = UnlikeSessionUseCase(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `세션 좋아요 취소 테스트`() = runBlocking {
        assertEquals(0, repository.getSessionLike().size)

        val sessions = repository.getSessionAll()

        // 좋아요 상태가 아닌 경우 무시
        useCase(sessions[0])
        assertEquals(0, repository.getSessionLike().size)

        // 좋아요 설정
        repository.likeSession(sessions[0])
        assertEquals(1, repository.getSessionLike().size)

        // 좋아요 취소
        useCase(sessions[0])
        assertEquals(0, repository.getSessionLike().size)
    }
}