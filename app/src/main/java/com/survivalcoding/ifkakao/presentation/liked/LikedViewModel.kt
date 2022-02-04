package com.survivalcoding.ifkakao.presentation.liked

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.usecase.GetAllLocalSessionDataUseCase
import com.survivalcoding.ifkakao.domain.usecase.GetAllSessionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class LikedViewModel @Inject constructor(
    private val getAllSessionsUseCase: GetAllSessionsUseCase,
    private val getAllLocalSessionDataUseCase: GetAllLocalSessionDataUseCase,
) : ViewModel() {
    private val _likedSession = getAllLocalSessionDataUseCase()
    private val _allSession = getAllSessionsUseCase()

    val likedSession = combine(_likedSession, _allSession) { like, all ->
        like.filter { it.isLiked }.map { local -> all.first { it.id == local.id } }
    }.asLiveData()
}