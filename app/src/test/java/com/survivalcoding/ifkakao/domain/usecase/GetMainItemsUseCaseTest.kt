package com.survivalcoding.ifkakao.domain.usecase

import com.survivalcoding.ifkakao.data.datasource.IkContentsDataSource
import com.survivalcoding.ifkakao.data.datasource.RetrofitClient
import com.survivalcoding.ifkakao.data.datasource.service.IkContentsService
import com.survivalcoding.ifkakao.data.repository.IkContentsRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMainItemsUseCaseTest {
    private lateinit var repository: IkContentsRepository
    private lateinit var service: IkContentsService

    private lateinit var getListItemsUseCase: GetListItemsUseCase
    private lateinit var getMainItemsUseCase: GetMainItemsUseCase

    @Before
    fun setUp() {
        service = RetrofitClient.getClient().create(IkContentsService::class.java)
        repository = IkContentsRepositoryImpl(IkContentsDataSource(service))

        getListItemsUseCase = GetListItemsUseCase(repository)
        getMainItemsUseCase = GetMainItemsUseCase(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    operator fun invoke() = runBlocking {
        when (val result = getListItemsUseCase()) {
            is Result.Error -> assertEquals(0, result.error)
            is Result.Success -> {
                val data = (result.data ?: listOf())

                val sb = StringBuilder()

                val videoLengthNull = data.filter { it.videos.any { it.videoLength == null } }
                videoLengthNull.forEach {
                    if (it.videos.any { it.videoLength == null })
                        sb.append(it.title).append('\n')
                }
                assertEquals(1, sb.toString())

                // 카카오 애자일 상담소: 비디오 길이 없음.
            }
        }
    }
}