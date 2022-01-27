package com.survivalcoding.ifkakao.presentation.main

import androidx.lifecycle.*
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.repository.SessionRepository
import com.survivalcoding.ifkakao.domain.usecase.GetHighLightedUseCase
import com.survivalcoding.ifkakao.domain.usecase.GetSessionsUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    getHighLightedUseCase: GetHighLightedUseCase,
) : ViewModel() {
    private var _infos = MutableLiveData(emptyList<Session>())
    val infos: LiveData<List<Session>> = _infos

    init {
        viewModelScope.launch {
            _infos.value = getHighLightedUseCase.invoke()
        }
    }
}

/*
class MainViewModelFactory(
    private val repository: SessionRepository,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(
                getHighLightedUseCase = GetHighLightedUseCase(
                    GetSessionsUseCase(repository)
                )
            ) as T
        else throw IllegalArgumentException()
    }
}*/