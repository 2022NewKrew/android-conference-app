package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase
import com.survivalcoding.ifkakao.presentation.detail.DetailUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.runBlocking
import java.util.*

class MainViewModel(
    private val getSessionsUseCase: GetSessionsUseCase,
) : ViewModel() {
    private var _allSessions = listOf<IkSessionData>()
    private val _filteredSessions = MutableStateFlow<List<IkSessionData>>(listOf())
    private val _selectedSession = MutableStateFlow<IkSessionData?>(null)
    private val _exposedListSize = MutableStateFlow(4)

    private val _sessionStack = Stack<ReplaceInfo?>().apply { push(null) }
    private val _prevScrollPosition = MutableStateFlow(0 to 0)
    private var _isBackPressed = false

    val detailUIState =
        combine(_filteredSessions, _exposedListSize, _selectedSession) { list, size, session ->
            DetailUIState(list, size, session)
        }.asLiveData()
    val filteredSessions = _filteredSessions.asLiveData()
    val scrollPosition = _prevScrollPosition.asLiveData()

    init {
        runBlocking {
            _allSessions = getSessionsUseCase()
        }
    }

    fun nextSession(sessionData: IkSessionData?, x: Int, y: Int) {
        _sessionStack.push(ReplaceInfo(sessionData, _exposedListSize.value, x, y))
        _selectedSession.value = sessionData
        _exposedListSize.value = 4
    }

    fun initViewModel(fragmentType: FragmentType) {
        if (_isBackPressed) {
            val stkTop = _sessionStack.peek()
            stkTop?.let { setViewModel(it) }
            _isBackPressed = false
        }
        when (fragmentType) {
            FragmentType.HIGHLIGHT -> {
                _filteredSessions.value = _allSessions.filter { it.isSpotlight }
            }
            FragmentType.DETAIL -> {
                _filteredSessions.value =
                    _allSessions.filter {
                        it.field == _selectedSession.value?.field ?: "" && it.id != _selectedSession.value?.id ?: -1
                    }
            }
        }
    }

    fun onBackPressed() {
        _sessionStack.pop()
        _isBackPressed = true
    }

    fun exposeMoreRelatedSessions() {
        _exposedListSize.value = _exposedListSize.value + 10
        val stkTop = _sessionStack.pop()
        _sessionStack.push(stkTop?.copy(exposeCount = stkTop.exposeCount + 10))
    }

    private fun setViewModel(ReplaceInfo: ReplaceInfo) {
        _exposedListSize.value = ReplaceInfo.exposeCount
        _selectedSession.value = ReplaceInfo.session
        _prevScrollPosition.value = ReplaceInfo.scrollX to ReplaceInfo.scrollY
    }
}

enum class FragmentType {
    HIGHLIGHT, DETAIL, BY_KEYWORD, BY_DAY
}