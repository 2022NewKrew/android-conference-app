package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.remote.MockRemoteDataSource
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetHighLightedUseCaseTest {
    private lateinit var useCase: GetHighLightedUseCase
    private lateinit var repository: SessionRemoteRepository

    @Before
    fun setUp() {
        repository = MockRemoteDataSource()
        useCase = GetHighLightedUseCase(GetSessionsUseCase(repository))
    }

    @Test
    fun 하이라이트만_가져왔는지_확인() = runBlocking {
        val list = useCase.invoke()
        assertEquals(1, list.size)
    }
}