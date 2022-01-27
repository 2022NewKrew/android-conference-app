package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.remote.MockRemoteDataSource
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetRelatedSessionsUseCaseTest {
    private lateinit var useCase: GetRelatedSessionsUseCase
    private lateinit var repository: SessionRemoteRepository

    @Before
    fun setUp() {
        repository = MockRemoteDataSource()
        useCase = GetRelatedSessionsUseCase(GetSessionsUseCase(repository))
    }

    @Test
    fun 연관_세션_가져오는지_확인() = runBlocking {
        val data = useCase.invoke("서비스", listOf("1Day"))
        assertEquals(1, data.size)
        assertEquals(20, data[0].idx)

        val data2 = useCase.invoke("기술", listOf("1Day"))
        assertEquals(0, data2.size)

        val data3 = useCase.invoke("기술", listOf())
        assertEquals(4, data3.size)
    }
}