package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.presentation.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow

class ActivityViewModel : ViewModel() {
    private val _isLogin = MutableStateFlow(LoginState.START)
    private val _isMaintainLogin = MutableStateFlow(false)

    val isLogin = _isLogin.asLiveData()
    val isMaintainLogin = _isMaintainLogin.asLiveData()

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.MaintainLogin -> {
                _isMaintainLogin.value = !_isMaintainLogin.value
            }
            is LoginEvent.Login -> {
                _isLogin.value = when {
                    event.id.isEmpty() -> LoginState.EMPTY_ID
                    event.password.isEmpty() -> LoginState.EMPTY_PASSWORD
                    event.id != Resource.ID || event.password != Resource.PASSWORD -> LoginState.FAIL
                    else -> LoginState.SUCCESS
                }
            }
            LoginEvent.IsLogin -> _isLogin.value = LoginState.SUCCESS
            LoginEvent.Logout -> _isLogin.value = LoginState.START
        }
    }
}

sealed class LoginEvent {
    object MaintainLogin : LoginEvent()
    data class Login(val id: String, val password: String) : LoginEvent()
    object IsLogin : LoginEvent()
    object Logout : LoginEvent()
}

enum class LoginState {
    START, EMPTY_ID, EMPTY_PASSWORD, FAIL, SUCCESS;
}