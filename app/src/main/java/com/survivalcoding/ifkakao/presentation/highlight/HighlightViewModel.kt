package com.survivalcoding.ifkakao.presentation.highlight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase
import com.survivalcoding.ifkakao.domain.usecase.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class HighlightViewModel(
    private val getSessionsUseCase: GetSessionsUseCase,
) : ViewModel() {
    private val _highlightLists = MutableStateFlow<List<IkSessionData>>(listOf())
    private val _state = MutableStateFlow<State>(State.Init)
    private val _message = MutableStateFlow("")

    val list = _highlightLists.asLiveData()
    val state = _state.asLiveData()
    val message = _message.asLiveData()

    fun loadHighlightSessions() =
        runBlocking {
            _state.value = State.Loading

            when (val list = getSessionsUseCase()) {
                is Result.Error -> {
                    _state.value = State.ListLoadingError
                }
                is Result.Success -> {
                    val highlight = list.data.filter { it.spotlightYn == "Y" }
                    _message.value = "${highlight.size} loaded"
                    _highlightLists.value = highlight
                    _state.value = State.NotLoading
                }
            }
        }
}

sealed class State {
    object Init : State()
    object ListLoadingError : State()
    object NotLoading : State()
    object Loading : State()
}