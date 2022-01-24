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

class GetContentUseCaseTest {
    private lateinit var repository: IkContentsRepository
    private lateinit var service: IkContentsService

    private lateinit var getSessionsUseCase: GetSessionsUseCase
    private lateinit var getContentUseCase: GetContentUseCase

    @Before
    fun setUp() {
        service = RetrofitClient.getClient().create(IkContentsService::class.java)
        repository = IkContentsRepositoryImpl(IkContentsDataSource(service))

        getSessionsUseCase = GetSessionsUseCase(repository)
        getContentUseCase = GetContentUseCase(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `list data get test`() = runBlocking {
        when (val result = getSessionsUseCase()) {
            is Result.Error -> assertEquals(0, result.error)
            is Result.Success -> {
                val data = result.data

                var sb = StringBuilder()

                val videoLengthNull =
                    data.filter { it.linkLists.video.any { it.description == "" } }
                videoLengthNull.forEach {
                    if (it.linkLists.video.any { it.description == "" })
                        sb.append(it.title).append('\n')
                }
                assertEquals("1", sb.toString())

                // 카카오 애자일 상담소: 비디오 길이 없음.

                sb = StringBuilder()
                val emptyExposure = data.filter { it.relationLists.mainExposureDay.isEmpty() }
                emptyExposure.forEach {
                    sb.append(it.title).append('\n')
                }
//                assertEquals("2", sb.toString())

                // 노출 날짜 없는 것들 많음.

                sb = StringBuilder()
                val highlightList = data.filter { it.spotlightYn == "Y" }
                highlightList.forEach {
                    sb.append(it.title).append('\n')
                }
//                assertEquals("4", sb.toString())
            }
        }
    }

    @Test
    fun `main data get test`() = runBlocking {
        when (val result = getContentUseCase()) {
            is Result.Error -> assertEquals(0, result.error)
            is Result.Success -> {
                val data = result.data

                var sb = StringBuilder()
                val moreOneSpeaker = data.sessions.filter { it.sessionSpeakers.size > 1 }
                moreOneSpeaker.forEach {
                    sb.append(it.title).append('\n')
                }
                assertEquals("3", sb.toString())

                // 여러명이 발표한 세션 다수


            }
        }
    }
}