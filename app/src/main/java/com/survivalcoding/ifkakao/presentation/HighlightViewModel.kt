package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkListItem
import com.survivalcoding.ifkakao.domain.usecase.GetListItemsUseCase
import com.survivalcoding.ifkakao.domain.usecase.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HighlightViewModel(
    private val getListItemsUseCase: GetListItemsUseCase,
) : ViewModel() {
    private val _highlightLists = MutableStateFlow<List<IkListItem>>(listOf())
    private val _state = MutableStateFlow<State>(State.Normal)

    init {
        viewModelScope.launch {
            when (val list = getListItemsUseCase()) {
                is Result.Error -> {
                    _state.value = State.ListLoadingError
                }
                is Result.Success -> {
                    _highlightLists.value = list.data.filter { it.isSpotlight }
                }
            }
        }
    }
}

sealed class State {
    object Normal : State()
    object ListLoadingError : State()
}