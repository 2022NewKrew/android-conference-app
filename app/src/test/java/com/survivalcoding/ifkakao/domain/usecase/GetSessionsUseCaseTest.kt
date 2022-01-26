package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.IkContentsDataSource
import com.survivalcoding.ifkakao.data.datasource.RetrofitClient
import com.survivalcoding.ifkakao.data.datasource.service.IkContentsService
import com.survivalcoding.ifkakao.data.repository.IkContentsRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import java.lang.StringBuilder

class GetSessionsUseCaseTest {
    private lateinit var service: IkContentsService
    private lateinit var repository: IkContentsRepository
    private lateinit var useCase: GetSessionsByTagUseCase

    @Before
    fun setUp() {
        service = RetrofitClient.getClient().create(IkContentsService::class.java)
        repository = IkContentsRepositoryImpl(IkContentsDataSource(service))
    }

    @Test
    operator fun invoke() {
    }
}