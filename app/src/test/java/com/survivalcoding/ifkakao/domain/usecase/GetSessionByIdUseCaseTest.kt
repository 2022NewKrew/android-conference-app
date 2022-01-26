package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.remote.MockRemoteDataSource
import com.survivalcoding.ifkakao.domain.repository.SessionRemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetSessionByIdUseCaseTest {
    private lateinit var useCase: GetSessionByIdUseCase
    private lateinit var repository: SessionRemoteRepository

    @Before
    fun setUp() {
        repository = MockRemoteDataSource()
        useCase = GetSessionByIdUseCase(GetSessionsUseCase(repository))
    }

    @Test
    fun ID_정보대로_받는지_확인() = runBlocking {
        val data = useCase.invoke(95)
        assertEquals("Klaytn을 이용하여 NFT 활용하기: 발행부터 판매까지", data?.title)
        assertEquals(emptyList<String>(), data?.relationList?.MAIN_EXPOSURE_DAY)

        val data2 = useCase.invoke(1)
        assertEquals(null, data2)
    }
}