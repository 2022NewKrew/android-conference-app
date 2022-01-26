package com.survivalcoding.ifkakao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Data
import com.example.domain.repository.ConferencesRepository
import com.example.domain.usecase.GetConferencesUseCase
import com.example.domain.usecase.GetHighlightSessionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getHighlightSessionsUseCase: GetHighlightSessionsUseCase
) : ViewModel() {

    val items = MutableStateFlow(listOf<Data>())

    init {
        viewModelScope.launch {
            items.value = getHighlightSessionsUseCase()
        }
    }
}