package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.like.LikeInMemoryDataSource
import com.survivalcoding.ifkakao.data.datasource.session.local.SessionLocalDataSource
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetAllSessionUseCaseTest {

    private lateinit var useCase: GetAllSessionUseCase
    private lateinit var repository: SessionRepository

    @Before
    fun setUp() {
        repository = SessionRepositoryImpl(SessionLocalDataSource(), LikeInMemoryDataSource())
        useCase = GetAllSessionUseCase(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `세션 리스트 조회 테스트`() = runBlocking {
        assert(useCase().isNotEmpty())
    }
}