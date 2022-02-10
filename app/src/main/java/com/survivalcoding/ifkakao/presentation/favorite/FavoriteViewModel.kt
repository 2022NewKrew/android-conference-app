package com.survivalcoding.ifkakao.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.usecase.GetLikeSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val getLikeSessionUseCase: GetLikeSessionUseCase) :
    ViewModel() {
    private val _sessions = MutableLiveData<List<Session>>()
    val sessions: LiveData<List<Session>> get() = _sessions

    fun getLikeSessions() = viewModelScope.launch {
        _sessions.value = getLikeSessionUseCase.invoke()
    }
}