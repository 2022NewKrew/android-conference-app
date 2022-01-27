package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.IfKakaoService
import com.survivalcoding.ifkakao.data.repository.IfKakaoRemoteRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.IfKakaoRepository

import org.junit.Before
import org.junit.Test

class GetSessionsUseCaseTest {
    private lateinit var service: IfKakaoService
    private lateinit var repository: IfKakaoRepository
    private lateinit var useCase: GetSessionsByTagUseCase

    @Before
    fun setUp() {
        service = RetrofitClient.getClient().create(IfKakaoService::class.java)
        repository = IfKakaoRemoteRepositoryImpl(IkRetrofitDataSource(service))
    }

    @Test
    operator fun invoke() {
    }
}