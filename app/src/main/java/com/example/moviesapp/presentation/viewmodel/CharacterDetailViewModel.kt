package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.use_case.get_character.GetCharacterUseCase
import com.example.moviesapp.helpers.Resource
import com.example.moviesapp.presentation.state.CharacterDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {
    private val _characterDetailState = MutableLiveData<CharacterDetailState>()
    val characterDetailState: LiveData<CharacterDetailState> get() = _characterDetailState

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            getCharacterUseCase(id = id).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _characterDetailState.value = CharacterDetailState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _characterDetailState.value = CharacterDetailState(
                            isLoading = false,
                            character = resource.data
                        )
                    }

                    is Resource.Error -> {
                        _characterDetailState.value = CharacterDetailState(
                            isLoading = false,
                            error = resource.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }
}