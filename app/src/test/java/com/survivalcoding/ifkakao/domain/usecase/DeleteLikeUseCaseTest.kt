package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.local.MockLocalDataSource
import com.survivalcoding.ifkakao.domain.repository.SessionLocalRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DeleteLikeUseCaseTest {
    private lateinit var getLikes: GetLikesUseCase
    private lateinit var useCase: DeleteLikeUseCase
    private lateinit var repository: SessionLocalRepository

    @Before
    fun setUp() {
        repository = MockLocalDataSource()
        getLikes = GetLikesUseCase(repository)
        useCase = DeleteLikeUseCase(repository)
    }

    @Test
    fun 데이터_삭제_확인() = runBlocking {
        useCase.invoke(123)
        assertEquals(1, getLikes.invoke().size)

        useCase.invoke(123)
        assertEquals(1, getLikes.invoke().size)

        useCase.invoke(20)
        assertEquals(0, getLikes.invoke().size)
    }
}