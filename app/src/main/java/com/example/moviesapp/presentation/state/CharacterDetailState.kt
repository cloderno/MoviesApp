package com.example.moviesapp.presentation.state

import com.example.moviesapp.domain.model.Character

data class CharacterDetailState (
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: String = ""
)