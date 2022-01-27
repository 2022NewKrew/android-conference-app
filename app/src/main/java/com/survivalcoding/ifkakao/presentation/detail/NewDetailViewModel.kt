package com.survivalcoding.ifkakao.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByTagUseCase
import com.survivalcoding.ifkakao.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NewDetailViewModel @Inject constructor(
    private val getSessionsByTagUseCase: GetSessionsByTagUseCase,
) : ViewModel() {
    val relatedSessions by lazy {
        getSessionsByTagUseCase {
            it.field == _currentSession.value.field && it.id != _currentSession.value.id
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UiState.Loading
        )
    }
    private val _relatedSessionsCount = MutableStateFlow(4)
    private val _currentSession = MutableStateFlow(IkSessionData.getEmptySessionData())

    val currentSession = _currentSession.asLiveData()
    val relatedSessionsCount = _relatedSessionsCount.asLiveData()

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.LoadingData -> {
                _relatedSessionsCount.value = event.info.relatedSessionsCount
                _currentSession.value = event.info.currentSession
            }
            DetailEvent.LoadMoreSessions -> {
                _relatedSessionsCount.value = _relatedSessionsCount.value + 10
            }
        }
    }

    fun getCount() = _relatedSessionsCount.value
}