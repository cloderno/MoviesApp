package com.example.moviesapp.presentation.state

import com.example.moviesapp.domain.model.Character

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = ""
)