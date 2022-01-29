package com.survivalcoding.ifkakao.presentation.highlight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.usecase.GetHighlightSessionsUseCase
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HighlightViewModel @Inject constructor(
    getHighlightSessionsUseCase: GetHighlightSessionsUseCase,
    private val stk: Stack<FragmentInformation>,
) : ViewModel() {
    val sessions = getHighlightSessionsUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = listOf()
        )

    fun toAllSession() {
        stk.push(FragmentInformation(exposedListCount = 8, selectedDay = 3))
    }
}