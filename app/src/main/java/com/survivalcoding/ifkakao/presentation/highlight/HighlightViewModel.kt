package com.survivalcoding.ifkakao.presentation.highlight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByTagUseCase
import com.survivalcoding.ifkakao.presentation.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HighlightViewModel @Inject constructor(
    getSessionsByTagUseCase: GetSessionsByTagUseCase,
) : ViewModel() {
    val uiState: StateFlow<UiState> = getSessionsByTagUseCase {
        it.isSpotlight
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = UiState.Loading
    )
    val backgroundMobile = "https://t1.kakaocdn.net/service_if_kakao_prod/images/mo/bg_bye_2021.png"
    val backgroundPc = "https://t1.kakaocdn.net/service_if_kakao_prod/images/pc/bg_bye_2021.png"
    val icon = "https://t1.kakaocdn.net/service_if_kakao_prod/images/ico_bye_2021.gif"
}