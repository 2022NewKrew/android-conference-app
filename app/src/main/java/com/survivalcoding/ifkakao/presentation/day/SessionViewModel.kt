package com.survivalcoding.ifkakao.presentation.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByDayUseCase
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getSessionsByDayUseCase: GetSessionsByDayUseCase,
    stk: Stack<FragmentInformation>
) : ViewModel() {
    private var _currentDay = MutableStateFlow(stk.peek().selectedDay)
    private var _exposedListCount = MutableStateFlow(stk.peek().exposedListCount)

    val sessions: StateFlow<List<IkSessionData>> =
        combine(_currentDay, _exposedListCount) { day, count ->
            getSessionsByDayUseCase(day, count)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = listOf()
        )
    val currentDay: StateFlow<Int> = _currentDay
    val exposedListCount: StateFlow<Int> = _exposedListCount

    fun onEvent(event: SessionEvent) {
        when (event) {
            is SessionEvent.ChangeDay -> {
                _currentDay.value = event.day
                _exposedListCount.value = 10
            }
            SessionEvent.LoadMoreSessions -> {
                _exposedListCount.value = _exposedListCount.value + 10
            }
        }
    }
}

sealed class SessionEvent {
    object LoadMoreSessions : SessionEvent()
    data class ChangeDay(val day: Int) : SessionEvent()
}