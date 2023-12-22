package com.example.moviesapp.domain.repository

import com.example.moviesapp.data.remote.dto.character.CharacterDTO
import com.example.moviesapp.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<CharacterDTO>
    suspend fun getCharacterById(id: Int): CharacterDTO
}