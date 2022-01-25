package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getSessionsUseCase: GetSessionsUseCase,
) : ViewModel() {
    private var _allSessions = listOf<IkSessionData>()
    private val _usedList = MutableStateFlow<List<IkSessionData>>(listOf())

    val usedList = _usedList.asLiveData()

    init {
        viewModelScope.launch {
            _allSessions = getSessionsUseCase()
            _usedList.value = _allSessions.filter { it.isSpotlight }
        }
    }

    fun setSessonType(sessionType: SessionType) {
        when (sessionType) {
            SessionType.HighlightSession -> {
                _usedList.value = _allSessions.filter { it.isSpotlight }
            }
        }
    }
}

sealed class SessionType {
    object HighlightSession : SessionType()
}