package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val getSessionsUseCase: GetSessionsUseCase,
) : ViewModel() {
    private var _allSessions = listOf<IkSessionData>()
    private val _usedList = MutableStateFlow<List<IkSessionData>>(listOf())
    private val _selectedSession = MutableStateFlow<IkSessionData?>(null)
    private val _listSize = MutableStateFlow(4)

    val usedList = _usedList.asLiveData()
    val selectedSession = _selectedSession.asLiveData()

    init {
        runBlocking { _allSessions = getSessionsUseCase() }
    }

    fun setSessionType(sessionType: SessionType) {
        when (sessionType) {
            SessionType.HighlightSession -> {
                _usedList.value = _allSessions.filter { it.isSpotlight }
            }
            is SessionType.RelativeSession -> {
                _usedList.value =
                    _allSessions.filter {
                        it.field == _selectedSession.value?.field ?: ""
                                && it.id != _selectedSession.value?.id ?: -1
                    }.take(_listSize.value)
            }
        }
    }

    fun selectSession(sessionData: IkSessionData?) {
        _selectedSession.value = sessionData
    }
}

sealed class SessionType {
    object HighlightSession : SessionType()
    object RelativeSession : SessionType()
}