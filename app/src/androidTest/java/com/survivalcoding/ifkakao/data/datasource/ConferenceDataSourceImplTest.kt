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
class ConferenceDataSourceImplTest {

    @get: Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var conferenceDataSourceImpl: ConferenceDataSourceImpl

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun getData() {
        runBlocking {
            val data = conferenceDataSourceImpl.getData()
            Log.d("data", data.toString())

            assertNotEquals(null, data)
            assertNotEquals(Unit, data)
        }
    }
}