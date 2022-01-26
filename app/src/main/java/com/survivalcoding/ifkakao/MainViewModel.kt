package com.survivalcoding.ifkakao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Data
import com.example.domain.repository.ConferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val conferencesRepository: ConferencesRepository
) : ViewModel() {

    val items = MutableStateFlow(listOf<Data>())

    init {
        viewModelScope.launch {
            items.value = conferencesRepository.getSpotLightedSessions()
        }
    }
}