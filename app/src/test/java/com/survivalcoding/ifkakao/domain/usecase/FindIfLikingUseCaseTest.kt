package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.local.MockLocalDataSource
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FindIfLikingUseCaseTest {
    private lateinit var useCase: FindIfLikingUseCase
    private lateinit var repository: SessionLocalRepository

    @Before
    fun setUp() {
        repository = MockLocalDataSource()
        useCase = FindIfLikingUseCase(repository)
    }

    @Test
    fun Like_고른걸_추출하는지_확인() = runBlocking {
        assertEquals(true, useCase.invoke(20))
        assertEquals(false, useCase.invoke(33))
    }
}