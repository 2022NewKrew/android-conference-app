package com.survivalcoding.ifkakao.presentation.highlight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetHighlightSessionsUseCase
import com.survivalcoding.ifkakao.presentation.util.FragmentInformation
import com.survivalcoding.ifkakao.presentation.util.FragmentType
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
        stk.push(
            FragmentInformation(
                fragmentType = FragmentType.SESSION,
                exposedListCount = 8,
                selectedDay = 3
            )
        )
    }

    fun toDetailSession(session: IkSessionData) {
        stk.push(
            FragmentInformation(
                fragmentType = FragmentType.DETAIL,
                session = session,
                exposedListCount = 4,
            )
        )
    }
}