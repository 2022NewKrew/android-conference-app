package com.survivalcoding.ifkakao.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetLocalSessionDataUseCase
import com.survivalcoding.ifkakao.domain.usecase.GetRelatedSessionsUseCase
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.FragmentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRelatedSessionsUseCase: GetRelatedSessionsUseCase,
    private val getLocalSessionDataUseCase: GetLocalSessionDataUseCase,
    private val stk: Stack<FragmentInformation>
) : ViewModel() {
    private val _currentSession = MutableStateFlow(stk.peek().session)
    private val _exposedListCount = MutableStateFlow(stk.peek().exposedListCount)
    var totalCount = 120

    val sessions = combine(_currentSession, _exposedListCount) { session, count ->
        val list = getRelatedSessionsUseCase(session)
        totalCount = list.size
        getRelatedSessionsUseCase(session).take(count)
    }.asLiveData()
    val currentSession = _currentSession.asLiveData()
    val exposedListCount = _exposedListCount.asLiveData()

    private val _localSessionData = getLocalSessionDataUseCase(_currentSession.value.id)
    val localSessionData = _localSessionData.asLiveData()

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.LoadMoreSessions -> {
                _exposedListCount.value = _exposedListCount.value + 10
            }
            is DetailEvent.NextSession -> {
                updateStack()
                stk.push(
                    FragmentInformation(
                        fragmentType = FragmentType.DETAIL,
                        session = event.session,
                        exposedListCount = 4,
                    )
                )
            }
            DetailEvent.ToAllSession -> {
                updateStack()
                stk.push(
                    FragmentInformation(
                        fragmentType = FragmentType.SESSION,
                        exposedListCount = 8,
                        selectedDay = 3
                    )
                )
            }
        }
    }

    private fun updateStack() {
        stk.pop()
        stk.push(
            FragmentInformation(
                fragmentType = FragmentType.DETAIL,
                session = _currentSession.value,
                exposedListCount = _exposedListCount.value,
            )
        )
    }
}

sealed class DetailEvent {
    object LoadMoreSessions : DetailEvent()
    object ToAllSession : DetailEvent()
    data class NextSession(val session: IkSessionData) : DetailEvent()
}