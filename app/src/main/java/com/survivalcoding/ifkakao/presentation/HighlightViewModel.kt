package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkListItem
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase
import com.survivalcoding.ifkakao.domain.usecase.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HighlightViewModel(
    private val getSessionsUseCase: GetSessionsUseCase,
) : ViewModel() {
    private val _highlightLists = MutableStateFlow<List<IkSessionData>>(listOf())
    private val _state = MutableStateFlow<State>(State.Normal)

    init {
        viewModelScope.launch {
            when (val list = getSessionsUseCase()) {
                is Result.Error -> {
                    _state.value = State.ListLoadingError
                }
                is Result.Success -> {
                    _highlightLists.value =
                        list.data.filter { it.spotlightYn == "Y" }.sortedBy { it.idx }
                }
            }
        }
    }
}

sealed class State {
    object Normal : State()
    object ListLoadingError : State()
}