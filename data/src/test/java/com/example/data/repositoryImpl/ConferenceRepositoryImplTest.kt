package com.example.data.repositoryImpl

import com.example.data.MockData
import com.example.data.network.IfKakaoService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConferenceRepositoryImplTest {

    private val testRetrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://raw.githubusercontent.com")
        .build()


    private val realMockRepository =
        ConferenceRepositoryImpl(testRetrofit.create(IfKakaoService::class.java))

    val fakeMockitoRepository = Mockito.mock(ConferenceRepositoryImpl::class.java)

    @Before
    fun setUp() {
        runBlocking {
            Mockito.`when`(fakeMockitoRepository.getConferences()).thenReturn(
                MockData.conference
            )
        }
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getConferencesFromAPI() = runBlocking {
        val result = realMockRepository.getConferences()
        assertEquals(120, result.data.size)
        assertEquals(true, result.success)
        assertEquals("카카오엔터프라이즈가 그려가고 있는  Enterprise IT", result.data[4].title)
        assertEquals("모두를 위한 서비스, 카카오가 만들어가는 세상", result.data[10].title)
        assertEquals("카카오 애자일 상담소 (3일차)", result.data[result.data.size - 1].title)
    }

    @Test
    fun getConferencesFromMock() = runBlocking {
        val result = fakeMockitoRepository.getConferences()
        assertEquals(3, result.data.size)
        assertEquals(true, result.success)
        assertEquals("모두를 위한 서비스, 카카오가 만들어가는 세상", result.data[0].title)
        assertEquals("카카오임팩트 펠로우십을 통해 본  사회 혁신가의 성장 요인", result.data[1].title)
        assertEquals("카카오워크가 그리는 일하는 방식의 미래:  카카오워크 안에서 이뤄지는 업무의 완결", result.data[2].title)
    }
}