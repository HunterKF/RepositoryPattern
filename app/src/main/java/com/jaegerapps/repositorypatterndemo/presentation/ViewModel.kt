package com.jaegerapps.repositorypatterndemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaegerapps.repositorypatterndemo.domain.repo.PhotosRepo
import com.jaegerapps.repositorypatterndemo.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repo: PhotosRepo
) : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState())
    val state = _state
    init {
        viewModelScope.launch {
            when (val result = async { repo.getPhotos() }.await()) {
                is Resource.Error ->{
                    _state.update {
                        it.copy(
                            errorMessage = result.throwable?.message
                        )
                    }
                }
                is Resource.Success -> {
                    if (!result.data.isNullOrEmpty()) {
                        _state.update {
                            it.copy(
                                photo = result.data
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(
                                errorMessage = "Unknown error"
                            )
                        }
                    }
                }
            }
        }
    }
}