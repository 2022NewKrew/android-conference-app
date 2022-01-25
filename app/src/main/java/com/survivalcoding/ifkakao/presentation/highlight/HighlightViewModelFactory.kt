package com.survivalcoding.ifkakao.presentation.highlight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase

class HighlightViewModelFactory(
    private val repository: IkContentsRepository,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HighlightViewModel::class.java)) {
            return HighlightViewModel(
                GetSessionsUseCase(repository)
            ) as T
        }
        return super.create(modelClass)
    }
}