package com.survivalcoding.ifkakao.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.usecase.GetSessionByIdUseCase
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsRelatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSessionByIdUseCase: GetSessionByIdUseCase,
    private val getSessionsRelatedUseCase: GetSessionsRelatedUseCase
) :
    ViewModel() {

    private val _session = MutableLiveData<Session>()
    val session: LiveData<Session> get() = _session
    private val _relatedSessions = MutableLiveData<List<Session>>()
    val relatedSessions: LiveData<List<Session>> get() = _relatedSessions

    fun getSessionById(id: Int) = viewModelScope.launch {
        _session.value = getSessionByIdUseCase.invoke(id)
    }

    fun getSessionsRelated(id: Int, field: String) = viewModelScope.launch {
        _relatedSessions.value = getSessionsRelatedUseCase.invoke(id, field)
    }
}