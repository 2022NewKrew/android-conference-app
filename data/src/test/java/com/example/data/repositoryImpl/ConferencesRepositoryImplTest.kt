package com.example.data.repositoryImpl

import com.example.domain.entity.Conference
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ConferencesRepositoryImplTest {

    private val mockRepository = Mockito.mock(ConferencesRepositoryImpl::class.java)

    @Before
    fun setUp() {
        val mockData: Conference = Gson().fromJson(JsonData.json, Conference::class.java)
        runBlocking {
            // json 결과
            Mockito.`when`(mockRepository.getConferences()).thenReturn(
                mockData
            )
            // sessions data 전체결과
            Mockito.`when`(mockRepository.getAllSessions()).thenReturn(
                mockData.data
            )
            // highlight 결과
            Mockito.`when`(mockRepository.getSpotLightedSessions()).thenReturn(
                mockData.data.filter {
                    it.spotlightYn == "Y"
                }
            )
            //날짜 필터
            Mockito.`when`(mockRepository.getSessionsFromDate(20211116)).thenReturn(
                mockData.data.filter {
                    it.reservationDate == 20211116
                }
            )
            Mockito.`when`(mockRepository.getSessionsFromDate(20211117)).thenReturn(
                mockData.data.filter {
                    it.reservationDate == 20211117
                }
            )
            Mockito.`when`(mockRepository.getSessionsFromDate(20211118)).thenReturn(
                mockData.data
            )
            // 키워드 필터
            Mockito.`when`(mockRepository.getSessionsWithKeyWords("Android")).thenReturn(
                mockData.data.filter {
                    it.relationList.techClassification.any { keyWord ->
                        keyWord == "Android"
                    }
                }
            )

            Mockito.`when`(mockRepository.getSessionsWithKeyWords("iOS")).thenReturn(
                mockData.data.filter {
                    it.relationList.techClassification.any { keyWord ->
                        keyWord == "iOS"
                    }
                }
            )
            Mockito.`when`(mockRepository.getSessionsWithKeyWords("iOS", "Android")).thenReturn(
                mockData.data.filter {
                    it.relationList.techClassification.any { keyWord ->
                        // todo varags와 같은 결과인지는 확인
                        keyWord == "iOS" || keyWord == "Android"
                    }
                }
            )

        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getConferences() = runBlocking {
        val result = mockRepository.getConferences()
        assertEquals(120, result.data.size)
        assertEquals(true, result.success)
        assertEquals("카카오엔터프라이즈가 그려가고 있는  Enterprise IT", result.data[4].title)
        assertEquals("모두를 위한 서비스, 카카오가 만들어가는 세상", result.data[10].title)
        assertEquals("카카오 애자일 상담소 (3일차)", result.data[result.data.size - 1].title)
    }

    @Test
    fun getAllSessions() = runBlocking {
        val result = mockRepository.getAllSessions()
        assertEquals(120, result.size)
    }

    @Test
    fun getSpotlightedSessions() = runBlocking {
        val result = mockRepository.getSpotLightedSessions()
        assertEquals(16, result.size)
    }

    @Test
    fun getSessionsFromDate() = runBlocking {
        var result = mockRepository.getSessionsFromDate(20211116)
        assertEquals(27, result.size)
        result = mockRepository.getSessionsFromDate(20211117)
        assertEquals(91, result.size)
        result = mockRepository.getSessionsFromDate(20211118)
        assertEquals(120, result.size)
    }

    @Test
    fun getSessionsWithKeyWords() = runBlocking {
        var result = mockRepository.getSessionsWithKeyWords("Android")
        assertEquals(4, result.size)
        result = mockRepository.getSessionsWithKeyWords("iOS")
        assertEquals(3, result.size)
        result = mockRepository.getSessionsWithKeyWords("iOS", "Android")
        assertEquals(6, result.size)
    }


}