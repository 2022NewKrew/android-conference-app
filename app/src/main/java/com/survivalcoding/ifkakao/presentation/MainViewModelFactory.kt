package com.survivalcoding.ifkakao.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.survivalcoding.ifkakao.domain.repository.IkContentsRepository
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase

class MainViewModelFactory(
    private val repository: IkContentsRepository,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(
                GetSessionsUseCase(repository)
            ) as T
        return super.create(modelClass)
    }
}