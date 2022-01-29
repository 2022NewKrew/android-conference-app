package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.data.repository.SessionRemoteRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.SessionRepository

import org.junit.Before
import org.junit.Test

class GetSessionsUseCaseTest {
    private lateinit var service: IfKakaoService
    private lateinit var repository: SessionRepository
    private lateinit var useCase: GetSessionsByTagUseCase

    @Before
    fun setUp() {
    }

    @Test
    operator fun invoke() {
    }
}