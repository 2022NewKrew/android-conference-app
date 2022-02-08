package com.survivalcoding.ifkakao.core.di

import com.survivalcoding.ifkakao.data.datasource.IfKakaoLocalDataSource
import com.survivalcoding.ifkakao.data.datasource.IfKakaoProtoDatastoreDataSource
import com.survivalcoding.ifkakao.data.datasource.IfKakaoRemoteDataSource
import com.survivalcoding.ifkakao.data.datasource.IfKakaoRetrofitDataSource
import com.survivalcoding.ifkakao.data.repository.IfKakaoRepositoryImpl
import com.survivalcoding.ifkakao.domain.repository.IfKakaoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IfKakaoBinderModule {
    @Binds
    @Singleton
    abstract fun bindIfKakaoRepository(ifKakaoRepositoryImpl: IfKakaoRepositoryImpl): IfKakaoRepository

    @Binds
    @Singleton
    abstract fun bindIfKakaoLocalDataSource(ifKakaoProtoDatastoreDataSource: IfKakaoProtoDatastoreDataSource): IfKakaoLocalDataSource

    @Binds
    @Singleton
    abstract fun bindIfKakaoRemoteDataSource(ifKakaoRetrofitDataSource: IfKakaoRetrofitDataSource): IfKakaoRemoteDataSource
}