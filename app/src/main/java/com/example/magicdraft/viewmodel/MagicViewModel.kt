package com.example.magicdraft.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.magicdraft.api.MagicRepository
import com.example.magicdraft.api.MagicRepositoryImpl
import com.example.magicdraft.model.states.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

const val TAG = "MagicViewModel"

@HiltViewModel
class MagicViewModel @Inject constructor(
    private val repository: MagicRepository,
    private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val viewModelSafeScope by lazy {
        viewModelScope + coroutineExceptionHandler
    }

    // For logging errors of the coroutine
    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                TAG,
                "Context: $coroutineContext\nMessage: ${throwable.localizedMessage}",
                throwable
            )
        }
    }

    private val _setList = MutableLiveData<UIState>()
    val setList: LiveData<UIState> get() = _setList

    private val _boosterPack = MutableLiveData<UIState>()
    val boosterPack: LiveData<UIState> get() = _boosterPack

    fun setSetLoading() { _setList.value = UIState.Loading }
    fun setBoosterLoading() { _boosterPack.value = UIState.Loading }

    fun getSets() {
        viewModelSafeScope.launch (dispatcher) {
            repository.getSets().collect {
                _setList.postValue(it)
            }
        }
    }

    fun drawBooster(set: String) {
        viewModelSafeScope.launch (dispatcher) {
            repository.drawBooster(set).collect {
                _boosterPack.postValue(it)
            }
        }
    }
}