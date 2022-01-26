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
    private lateinit var useCase: GetSessionsUseCase

    @Before
    fun setUp() {
        service = RetrofitClient.getClient().create(IkContentsService::class.java)
        repository = IkContentsRepositoryImpl(IkContentsDataSource(service))
        useCase = GetSessionsUseCase(repository)
    }

    @Test
    operator fun invoke() = runBlocking {
        val list = useCase()
        assertEquals(120, list.size)

        val sb = StringBuilder()
        list[30].tag.forEach { sb.append("$it\n") }
        assertEquals("", sb.toString())
    }
}