package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.local.MockLocalDataSource
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetLikesUseCaseTest {
    private lateinit var useCase: GetLikesUseCase
    private lateinit var repository: SessionLocalRepository

    @Before
    fun setUp() {
        repository = MockLocalDataSource()
        useCase = GetLikesUseCase(repository)
    }

    @Test
    fun like_수만큼_뽑고_정렬되는지_확인() = runBlocking {
        val data = useCase.invoke()
        assertEquals(2, data.size)
        assertEquals(20, data[0].idx)
        assertEquals(123, data[1].idx)
    }
}