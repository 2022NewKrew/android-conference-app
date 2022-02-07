package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.local.MockLocalDataSource
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AddLikeUseCaseTest {
    private lateinit var getLikes: GetLikesUseCase
    private lateinit var useCase: AddLikeUseCase
    private lateinit var repository: SessionLocalRepository

    @Before
    fun setUp() {
        repository = MockLocalDataSource()
        getLikes = GetLikesUseCase(repository)
        useCase = AddLikeUseCase(repository)
    }

    @Test
    fun 데이터_추가_확인() = runBlocking {
        useCase.invoke(1)
        assertEquals(3, getLikes.invoke().size)

        useCase.invoke(123)
        assertEquals(3, getLikes.invoke().size)

        useCase.invoke(2)
        assertEquals(4, getLikes.invoke().size)
    }
}