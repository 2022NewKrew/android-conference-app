package com.survivalcoding.ifkakao.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.usecase.GetSessionByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val getSessionByIdUseCase: GetSessionByIdUseCase) :
    ViewModel() {

    private val _session = MutableLiveData<Session>()
    val session: LiveData<Session> get() = _session

    fun getSessionById(id: Int) = viewModelScope.launch {
        _session.value = getSessionByIdUseCase.invoke(id)
    }
}