package com.survivalcoding.ifkakao.domain.usecase

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.survivalcoding.ifkakao.data.datasource.like.LikeDatabase
import com.survivalcoding.ifkakao.data.datasource.like.LikeRoomDataSource
import com.survivalcoding.ifkakao.data.datasource.session.local.SessionLocalDataSource
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetLikeSessionUseCaseTest {

    private lateinit var useCase: GetLikeSessionUseCase
    private lateinit var repository: SessionRepository
    private lateinit var db: LikeDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            LikeDatabase::class.java
        ).build()

        repository =
            SessionRepositoryImpl(SessionLocalDataSource(), LikeRoomDataSource(db.likeDao()))
        useCase = GetLikeSessionUseCase(repository)
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }

    @Test
    fun `room 세션 좋아요 테스트`() = runBlocking {
        Assert.assertEquals(0, useCase().size)

        val sessions = repository.getSessionAll()

        // 좋아요 상태가 아닌 경우 취소 무시
        repository.unlikeSession(sessions[0])
        Assert.assertEquals(0, useCase().size)

        // 좋아요 설정
        repository.likeSession(sessions[0])
        Assert.assertEquals(1, useCase().size)

        // 좋아요 취소
        repository.unlikeSession(sessions[0])
        Assert.assertEquals(0, useCase().size)
    }
}