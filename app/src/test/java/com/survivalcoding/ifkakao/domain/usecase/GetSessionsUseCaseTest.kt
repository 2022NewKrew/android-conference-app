package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.data.repository.SessionRemoteRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import com.survivalcoding.ifkakao.presentation.util.Keywords
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetSessionsUseCaseTest {
    private lateinit var service: IfKakaoService
    private lateinit var repository: SessionRepository
    private lateinit var useCase: GetSessionsByConditionUseCase

    @Before
    fun setUp() {
        service = Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IfKakaoService::class.java)
        repository = SessionRemoteRepositoryImpl(service)
        runBlocking { useCase = GetSessionsByConditionUseCase(repository.getContent()) }
    }

    @Test
    operator fun invoke() = runBlocking {
        assertEquals(28, useCase(1, 120, keywords = Keywords()).size)
        assertEquals(91, useCase(2, 120, keywords = Keywords()).size)
        assertEquals(120, useCase(3, 120, keywords = Keywords()).size)
    }
}