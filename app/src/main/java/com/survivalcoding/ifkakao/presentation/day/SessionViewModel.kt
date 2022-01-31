package com.survivalcoding.ifkakao.presentation.day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByDayUseCase
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getSessionsByDayUseCase: GetSessionsByDayUseCase,
    private val stk: Stack<FragmentInformation>
) : ViewModel() {
    private val _currentDay = MutableStateFlow(stk.peek().selectedDay)
    private val _exposedListCount = MutableStateFlow(stk.peek().exposedListCount)
    private val _keywords = MutableStateFlow(stk.peek().keywords)
    val isChanged = MutableStateFlow(false)

    val sessions = combine(_currentDay, _exposedListCount, _keywords) { day, count, keywords ->
        getSessionsByDayUseCase(day, count, keywords)
    }.asLiveData()
    val currentDay: StateFlow<Int> = _currentDay
    val changeObserve = isChanged.asLiveData()

    fun onEvent(event: SessionEvent) {
        when (event) {
            is SessionEvent.ChangeDay -> {
                _currentDay.value = event.day
                _exposedListCount.value = 10
            }
            SessionEvent.LoadMoreSessions -> {
                _exposedListCount.value = _exposedListCount.value + 10
            }
            SessionEvent.Update -> {
                val top = stk.peek().keywords
                _keywords.value = stk.peek().keywords
            }
        }
    }
}

sealed class SessionEvent {
    object LoadMoreSessions : SessionEvent()
    data class ChangeDay(val day: Int) : SessionEvent()
    object Update : SessionEvent()
}