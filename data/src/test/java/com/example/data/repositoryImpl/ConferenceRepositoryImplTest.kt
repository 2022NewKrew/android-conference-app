package com.example.data.repositoryImpl

import com.example.data.network.IfKakaoService
import com.example.domain.entity.Conference
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConferenceRepositoryImplTest {
    
    private val mockRepository = Mockito.mock(ConferenceRepositoryImpl::class.java)

    @Before
    fun setUp() {
        val mockData = Gson().fromJson(JsonData.json, Conference::class.java)
        runBlocking {
            Mockito.`when`(mockRepository.getConferences()).thenReturn(
                mockData
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

}