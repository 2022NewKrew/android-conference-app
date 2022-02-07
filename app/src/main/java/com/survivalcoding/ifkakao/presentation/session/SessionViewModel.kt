package com.survivalcoding.ifkakao.presentation.session

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByConditionUseCase
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.FragmentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getSessionsByConditionUseCase: GetSessionsByConditionUseCase,
    private val stk: Stack<FragmentInformation>
) : ViewModel() {
    private val _currentDay = MutableStateFlow(stk.peek().selectedDay)
    private val _exposedListCount = MutableStateFlow(stk.peek().exposedListCount)
    private val _keywords = MutableStateFlow(stk.peek().keywords)

    val isChanged = MutableLiveData(false)
    val sessions: StateFlow<List<IkSessionData>> =
        combine(_currentDay, _exposedListCount, _keywords) { day, count, keywords ->
            getSessionsByConditionUseCase(day, count, keywords)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = listOf()
        )
    val currentDay: StateFlow<Int> = _currentDay

    fun onEvent(event: SessionEvent) {
        when (event) {
            is SessionEvent.ChangeDay -> {
                _currentDay.value = event.day
                _exposedListCount.value = 10
            }
            SessionEvent.LoadMoreSessions -> {
                _exposedListCount.value = _exposedListCount.value + 10
            }
            SessionEvent.Update -> _keywords.value = stk.peek().keywords
            is SessionEvent.NextSession -> {
                stk.pop()
                stk.push(
                    FragmentInformation(
                        fragmentType = FragmentType.SESSION,
                        selectedDay = _currentDay.value,
                        exposedListCount = _exposedListCount.value,
                        keywords = _keywords.value,
                    )
                )
                stk.push(
                    FragmentInformation(
                        fragmentType = FragmentType.DETAIL,
                        session = event.session,
                        exposedListCount = 4,
                    )
                )
            }
        }
    }
}

sealed class SessionEvent {
    object LoadMoreSessions : SessionEvent()
    data class ChangeDay(val day: Int) : SessionEvent()
    object Update : SessionEvent()
    data class NextSession(val session: IkSessionData) : SessionEvent()
}