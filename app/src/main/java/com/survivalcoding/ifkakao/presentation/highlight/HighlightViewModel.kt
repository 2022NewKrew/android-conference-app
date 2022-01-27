package com.survivalcoding.ifkakao.presentation.highlight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.usecase.GetAllSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighlightViewModel @Inject constructor(private val getAllSessionUseCase: GetAllSessionUseCase) :
    ViewModel() {
    private val _sessions = MutableLiveData<List<Session>>()
    val sessions: LiveData<List<Session>> get() = _sessions

    init {
        getAllSessions()
    }

    private fun getAllSessions() = viewModelScope.launch {
        _sessions.value = getAllSessionUseCase.invoke()
    }
}