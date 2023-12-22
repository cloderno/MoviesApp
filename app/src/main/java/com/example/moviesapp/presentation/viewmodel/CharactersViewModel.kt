package com.example.moviesapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.domain.use_case.get_characters.GetCharactersUseCase
import com.example.moviesapp.presentation.state.CharacterListState
import com.example.moviesapp.helpers.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
): ViewModel() {

    private val _characterListState = MutableLiveData<CharacterListState>()
    val characterListState: LiveData<CharacterListState> get() = _characterListState

    init {
        fetchCharacters()
    }

    private fun fetchCharacters() {
        viewModelScope.launch {
            getCharactersUseCase().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _characterListState.value = CharacterListState(isLoading = true)
                    }

                    is Resource.Success -> {
                        _characterListState.value = CharacterListState(
                            isLoading = false,
                            characters = resource.data ?: emptyList()
                        )
                    }

                    is Resource.Error -> {
                        _characterListState.value = CharacterListState(
                            isLoading = false,
                            error = resource.message ?: "An unexpected error occurred"
                        )
                    }
                }
            }
        }
    }



//    private val _characters = MutableLiveData<Resource<List<Character>>>()
//    val characters: LiveData<Resource<List<Character>>> get() = _characters
//
//    private val _selectedCharacter = MutableLiveData<Resource<Character>>()
//    val selectedCharacter: LiveData<Resource<Character>> get() = _selectedCharacter
//
//    init {
//        Log.d("TAG", "ViewModel created")
//    }
//
//    fun loadCharacters() {
//        viewModelScope.launch {
//            _characters.value = Resource.Loading()
//            try {
//                val result = characterRepository.getCharacters()
//                _characters.value = Resource.Success(result)
//            } catch (e: Exception) {
//                _characters.value = Resource.Error(e.message ?: "An error occurred")
//            }
//        }
//    }
//
//    fun loadCharacterById(id: Int) {
//        viewModelScope.launch {
//            _selectedCharacter.value = Resource.Loading()
//            try {
//                val result = characterRepository.getCharacterById(id = id)
//                _selectedCharacter.value = Resource.Success(result)
//            } catch (e: Exception) {
//                _selectedCharacter.value = Resource.Error(e.message ?: "An error occurred")
//            }
//        }
//    }
}