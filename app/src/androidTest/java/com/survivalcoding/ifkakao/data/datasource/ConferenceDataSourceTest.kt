package com.survivalcoding.ifkakao.data.datasource

import android.util.Log
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ConferenceDataSourceTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var conferenceService: ConferenceService

    lateinit var conferenceDataSource: ConferenceDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
        conferenceDataSource = ConferenceDataSource(conferenceService)
    }

    @Test
    fun getData() {
        runBlocking {
            val data = conferenceDataSource.getData()
            Log.d("data", data.toString())

            assertNotEquals(null, data)
            assertNotEquals(Unit, data)
        }
    }
}