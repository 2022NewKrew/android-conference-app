package com.survivalcoding.ifkakao

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.repository.ConferencesRepository

class MainViewModelProvider(
    private val conferencesRepository: ConferencesRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                conferencesRepository,
            ) as T
        }
        return super.create(modelClass)
    }
}
