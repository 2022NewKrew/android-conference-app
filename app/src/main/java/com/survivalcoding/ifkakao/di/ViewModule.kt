package com.survivalcoding.ifkakao.di

import android.graphics.Color
import com.survivalcoding.ifkakao.presentation.util.SessionItemDecoration
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object ViewModule {
    @Provides
    @FragmentScoped
    fun provideSessionItemDecoration(): SessionItemDecoration =
        SessionItemDecoration(
            height = 3F,
            padding = 70F,
            color = Color.rgb(57, 57, 57)
        )
}