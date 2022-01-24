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
    fun `list data get test`() = runBlocking {
        when (val result = getListItemsUseCase()) {
            is Result.Error -> assertEquals(0, result.error)
            is Result.Success -> {
                val data = (result.data ?: listOf())

                var sb = StringBuilder()

                val videoLengthNull = data.filter { it.videos.any { it.videoLength == null } }
                videoLengthNull.forEach {
                    if (it.videos.any { it.videoLength == null })
                        sb.append(it.title).append('\n')
                }
//                assertEquals("1", sb.toString())

                // 카카오 애자일 상담소: 비디오 길이 없음.

                sb = StringBuilder()
                val emptyExposure = data.filter { it.exposureDay.isEmpty() }
                emptyExposure.forEach {
                    sb.append(it.title).append('\n')
                }
//                assertEquals("2", sb.toString())

                // 노출 날짜 없는 것들 많음.

                sb = StringBuilder()
                val highlightList = data.filter { it.isSpotlight }
                highlightList.forEach {
                    sb.append(it.title).append('\n')
                }
                assertEquals("4", sb.toString())
            }
        }
    }

    @Test
    fun `main data get test`() = runBlocking {
        when (val result = getMainItemsUseCase()) {
            is Result.Error -> assertEquals(0, result.error)
            is Result.Success -> {
                val data = (result.data ?: listOf())

                var sb = StringBuilder()
                val moreOneSpeaker = data.filter { it.speakerList.size > 1 }
                moreOneSpeaker.forEach {
                    sb.append(it.title).append('\n')
                }
                assertEquals("3", sb.toString())

                // 여러명이 발표한 세션 다수


            }
        }
    }
}