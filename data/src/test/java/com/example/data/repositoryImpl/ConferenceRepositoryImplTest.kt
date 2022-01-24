package com.example.data.repositoryImpl

import com.example.data.network.IfKakaoService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConferenceRepositoryImplTest {

    private val testRetrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://raw.githubusercontent.com")
        .build()

    private val testService =
        ConferenceRepositoryImpl(testRetrofit.create(IfKakaoService::class.java))

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getConferences() = runBlocking {
        val result = testService.getConferences()
        assertEquals(120, result.data.size)
    }
}