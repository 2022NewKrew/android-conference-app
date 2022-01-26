package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.like.LikeInMemoryDataSource
import com.survivalcoding.ifkakao.data.datasource.session.local.SessionLocalDataSource
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LikeSessionUseCaseTest {

    private lateinit var useCase: LikeSessionUseCase
    private lateinit var repository: SessionRepository

    @Before
    fun setUp() {
        repository = SessionRepositoryImpl(SessionLocalDataSource(), LikeInMemoryDataSource())
        useCase = LikeSessionUseCase(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `세션 좋아요 설정 테스트`() = runBlocking {
        assertEquals(0, repository.getSessionLike().size)

        val sessions = repository.getSessionAll()
        useCase(sessions[0])
        assertEquals(1, repository.getSessionLike().size)

        // 이미 좋아요 설정 한 경우 무시
        useCase(sessions[0])
        assertEquals(1, repository.getSessionLike().size)
    }
}