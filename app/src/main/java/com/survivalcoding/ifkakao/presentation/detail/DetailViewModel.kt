package com.survivalcoding.ifkakao.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetRelatedSessionsUseCase
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.take
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRelatedSessionsUseCase: GetRelatedSessionsUseCase,
    private val stk: Stack<FragmentInformation>
) : ViewModel() {
    private val _currentSession = MutableStateFlow(stk.peek().session)
    private val _exposedListCount = MutableStateFlow(stk.peek().exposedListCount)

    val sessions = combine(_currentSession, _exposedListCount) { session, count ->
        getRelatedSessionsUseCase(session).take(count)
    }.asLiveData()
    val currentSession = _currentSession.asLiveData()

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.LoadMoreSessions -> {
                _exposedListCount.value = _exposedListCount.value + 10
            }
            is DetailEvent.NextSession -> {
                stk.pop()
                stk.push(
                    FragmentInformation(
                        session = _currentSession.value,
                        exposedListCount = _exposedListCount.value,
                    )
                )
                stk.push(
                    FragmentInformation(
                        session = event.session
                    )
                )
            }
        }
    }
}

sealed class DetailEvent {
    object LoadMoreSessions : DetailEvent()
    data class NextSession(val session: IkSessionData) : DetailEvent()
}