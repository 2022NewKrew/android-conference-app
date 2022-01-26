package com.survivalcoding.ifkakao.presentation.highlight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.data.datasource.like.LikeInMemoryDataSource
import com.survivalcoding.ifkakao.data.datasource.session.local.SessionLocalDataSource
import com.survivalcoding.ifkakao.data.repository.SessionRepositoryImpl
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.usecase.GetAllSessionUseCase
import kotlinx.coroutines.launch

class HighlightViewModel : ViewModel() {
    private val repository =
        SessionRepositoryImpl(SessionLocalDataSource(), LikeInMemoryDataSource())
    private val getAllSessionUseCase = GetAllSessionUseCase(repository)
    private val _sessions = MutableLiveData<List<Session>>()
    val sessions: LiveData<List<Session>> get() = _sessions

    init {
        getAllSessions()
    }

    private fun getAllSessions() = viewModelScope.launch {
        _sessions.value = getAllSessionUseCase.invoke()
    }
}