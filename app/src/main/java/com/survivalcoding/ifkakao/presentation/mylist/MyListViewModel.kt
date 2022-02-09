package com.survivalcoding.ifkakao.presentation.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.survivalcoding.ifkakao.domain.model.Like
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.usecase.GetLikesUseCase
import com.survivalcoding.ifkakao.domain.usecase.GetSessionByIdUseCase
import kotlinx.coroutines.launch

class MyListViewModel(
    private val getLikedsUseCase: GetLikesUseCase,
    private val getSessionByIdUseCase: GetSessionByIdUseCase
) : ViewModel() {
    private var favorites: List<Like> = emptyList()

    private var _favoriteSessions: MutableLiveData<List<Session>> = MutableLiveData(emptyList())
    val favoriteSessions: LiveData<List<Session>> = _favoriteSessions

    init {
        updateData()
    }

    fun updateData() {
        viewModelScope.launch {
            favorites = getLikedsUseCase.invoke()
            var sessions = mutableListOf<Session>()
            for (fav in favorites) {
                getSessionByIdUseCase(fav.idx)?.let { sessions.add(it) }
            }
            _favoriteSessions.value = sessions
        }
    }
}