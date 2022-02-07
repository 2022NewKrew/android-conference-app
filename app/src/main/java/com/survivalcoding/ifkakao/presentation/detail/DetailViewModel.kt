package com.survivalcoding.ifkakao.presentation.detail

import androidx.lifecycle.*
import com.survivalcoding.ifkakao.domain.model.Session
import com.survivalcoding.ifkakao.domain.usecase.*
import com.survivalcoding.ifkakao.presentation.main.MainFragment.Companion.SELECTED
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val getSessionById: GetSessionByIdUseCase,
    private val getRelatedSessionsUseCase: GetRelatedSessionsUseCase,
    private val getIfLikingUseCase: FindIfLikingUseCase,
    private val addLikeUseCase: AddLikeUseCase,
    private val deleteLikeUseCase: DeleteLikeUseCase
) : ViewModel() {
    private var _session: MutableLiveData<Session> = MutableLiveData(Session())
    val session: LiveData<Session> = _session

    private var _relatedSessions: MutableLiveData<List<Session>> = MutableLiveData(emptyList())
    val relatedSessions: LiveData<List<Session>> = _relatedSessions

    private var _isLiking: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLiking: LiveData<Boolean> = _isLiking

    init {
        viewModelScope.launch {
            savedStateHandle.get<Int>(SELECTED)?.let { it ->
                _session.value = getSessionById.invoke(it)

                _relatedSessions.value = getRelatedSessionsUseCase.invoke(
                    _session.value!!.field,
                    _session.value!!.relationList.TECH_CLASSIFICATION,
                    _session.value!!.idx
                )

                _isLiking.value = getIfLikingUseCase.invoke(_session.value!!.idx)
            }
        }
    }

    fun setLike() {
        _isLiking.value = !(_isLiking.value)!!
        val idx = _session.value!!.idx
        viewModelScope.launch {
            if (_isLiking.value == true) addLikeUseCase.invoke(idx)
            else deleteLikeUseCase.invoke(idx)
        }
    }

}