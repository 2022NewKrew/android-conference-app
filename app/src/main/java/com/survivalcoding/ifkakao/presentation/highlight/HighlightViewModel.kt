package com.survivalcoding.ifkakao.presentation.highlight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.survivalcoding.ifkakao.domain.model.IkSessionData
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsByTagUseCase
import kotlinx.coroutines.flow.MutableStateFlow

class HighlightViewModel(
    getSessionsByTagUseCase: GetSessionsByTagUseCase,
) : ViewModel() {
    val highlightSessions = getSessionsByTagUseCase { it.isSpotlight }
}

class HighlightViewModelFactory(
    private val allSessions: List<IkSessionData>,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighlightViewModel::class.java))
            return HighlightViewModel(
                GetSessionsByTagUseCase(allSessions)
            ) as T
        return super.create(modelClass)
    }
}