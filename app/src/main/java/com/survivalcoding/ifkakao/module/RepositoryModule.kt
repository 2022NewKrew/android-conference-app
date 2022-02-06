package com.survivalcoding.ifkakao.module

import com.example.data.repositoryImpl.ConferencesRepositoryImpl
import com.example.data.repositoryImpl.LikeRepositoryImpl
import com.example.domain.repository.ConferencesRepository
import com.example.domain.repository.LikeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindConferenceRepository(conferencesRepositoryImpl: ConferencesRepositoryImpl):
            ConferencesRepository

    @Binds
    fun bindKikeRepository(likeRepositoryImpl: LikeRepositoryImpl):
            LikeRepository


}