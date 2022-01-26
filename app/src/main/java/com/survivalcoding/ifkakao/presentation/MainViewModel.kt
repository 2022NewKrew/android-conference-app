package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*

class MainViewModel(
    private val getSessionsUseCase: GetSessionsUseCase,
) : ViewModel() {
    private var _allSessions = listOf<IkSessionData>()
    private val _usedList = MutableStateFlow<List<IkSessionData>>(listOf())
    private val _selectedSession = MutableStateFlow<IkSessionData?>(null)
    private val _listSize = MutableStateFlow(4)

    private val _sessionStack = Stack<IkSessionData?>().apply { push(null) }
    private var _isBackPressed = false

    val usedList = _usedList.asLiveData()
    val selectedSession = _selectedSession.asLiveData()

    init {
        runBlocking {
            _allSessions = getSessionsUseCase()
        }
    }

    /**
     * fragment 이동할 때, detail fragment로 이동하는 경우에는 IkSessionData를 넣어주고 이동한다.
     * 그렇지 않은 경우에는, null을 넣어주고 이동한다.
     */
    fun nextSession(sessionData: IkSessionData?) {
        _sessionStack.push(sessionData)
        _selectedSession.value = sessionData
    }

    fun initViewModel(sessionType: SessionType) {
        if (_isBackPressed) {
            _selectedSession.value = if (_sessionStack.isEmpty()) null else _sessionStack.peek()
            _isBackPressed = false
        }
        when (sessionType) {
            SessionType.HighlightSession -> {
                _usedList.value = _allSessions.filter { it.isSpotlight }
            }
            is SessionType.RelativeSession -> {
                _listSize.value = 4
                _usedList.value =
                    _allSessions.filter {
                        it.field == _selectedSession.value?.field ?: ""
                                && it.id != _selectedSession.value?.id ?: -1
                    }.take(_listSize.value)
            }
        }
    }

    fun onBackPressed() {
        _sessionStack.pop()
        _isBackPressed = true
    }
}

sealed class SessionType {
    object HighlightSession : SessionType()
    object RelativeSession : SessionType()
}