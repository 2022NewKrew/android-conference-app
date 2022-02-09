package com.survivalcoding.ifkakao.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.usecase.GetFavoriteSessionsUseCase
import com.survivalcoding.ifkakao.presentation.common.CommonBinder
import com.survivalcoding.ifkakao.presentation.common.FooterBinder
import com.survivalcoding.ifkakao.presentation.common.SessionListItemBinder
import com.survivalcoding.ifkakao.presentation.common.SessionTitleBinder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val getFavoriteSessionsUseCase: GetFavoriteSessionsUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UiState(listOf()))
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun refreshList() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(binderList = initBinderList())
        }
    }

    private suspend fun initBinderList(): List<CommonBinder> {
        val binderList = mutableListOf<CommonBinder>()

        binderList.add(SessionTitleBinder("Favorites"))
        binderList.addAll(getFavoriteSessionsUseCase().map {
            SessionListItemBinder(
                it
            ) { sendEvent(Event.NavigateToSessionDetail(it.idx)) }
        })
        binderList.add(FooterBinder())

        return binderList
    }

    private fun sendEvent(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    data class UiState(
        val binderList: List<CommonBinder>
    )

    sealed class Event {
        class NavigateToSessionDetail(val idx: Int) : Event()
    }
}