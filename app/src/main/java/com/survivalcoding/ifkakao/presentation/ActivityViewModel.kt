package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow

class ActivityViewModel : ViewModel() {
    private val _isLogin = MutableStateFlow(false)
    private val _isMaintainLogin = MutableStateFlow(false)

    val isLogin = _isLogin.asLiveData()
    val isMaintainLogin = _isMaintainLogin.asLiveData()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.MaintainLogin -> {
                _isMaintainLogin.value = !_isMaintainLogin.value
            }
        }
    }
}

sealed class LoginEvent {
    object MaintainLogin : LoginEvent()
}