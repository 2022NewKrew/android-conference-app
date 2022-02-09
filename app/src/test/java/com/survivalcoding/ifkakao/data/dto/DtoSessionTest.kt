package com.survivalcoding.ifkakao.data.dto

import com.google.gson.Gson
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DtoSessionTest {

    private val mockAllNullSessionJson = "{}"
    private lateinit var mockSession: Session

    @Before
    fun setUp() {
        mockSession = Gson().fromJson(mockAllNullSessionJson, Session::class.java)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `비디오가 없는 경우 convert 테스트`() {
        val noVideoSession = mockSession.convert()
        Assert.assertEquals(listOf<String>(), noVideoSession.videos)
    }

    @Test
    fun `발표자가 많은 경우 convert 테스트`() {
        val manySpeakerSession = mockSession.convert()
        Assert.assertEquals(listOf<String>(), manySpeakerSession.contentsSpeakers)
    }

    @Test
    fun `모든 속성이 null인 경우 convert 테스트`() {
        val convertedMockSession = mockSession.convert()

        Assert.assertNotEquals(null, convertedMockSession.title)
        Assert.assertNotEquals(null, convertedMockSession.idx)
        Assert.assertNotEquals(null, convertedMockSession.categories)
        Assert.assertNotEquals(null, convertedMockSession.exposureDay)
        Assert.assertNotEquals(null, convertedMockSession.company)
        Assert.assertNotEquals(null, convertedMockSession.content)
        Assert.assertNotEquals(null, convertedMockSession.contentTag)
        Assert.assertNotEquals(null, convertedMockSession.contentsSpeakers)
        Assert.assertNotEquals(null, convertedMockSession.isFavorite)
        Assert.assertNotEquals(null, convertedMockSession.isHighlight)
        Assert.assertNotEquals(null, convertedMockSession.thumbnailUrl)
        Assert.assertNotEquals(null, convertedMockSession.videos)
    }
}