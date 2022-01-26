package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.remote.MockRemoteDataSource
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetSessionsUseCaseTest {
    private lateinit var useCase: GetSessionsUseCase
    private lateinit var repository: SessionRemoteRepository

    @Before
    fun setUp() {
        repository = MockRemoteDataSource()
        useCase = GetSessionsUseCase(repository)
    }

    @Test
    fun 값_확인() = runBlocking {
        val list = useCase.invoke()
        assertEquals(list.size, 5)
        assertEquals(list[0].linkList?.VIDEO?.get(0)?.idx, 3683)
        assertEquals(list[4].linkList?.VIDEO?.get(0)?.description, null)
        assertEquals(list[1].linkList?.VIDEO?.get(0)?.description, "17:47")
    }

}
