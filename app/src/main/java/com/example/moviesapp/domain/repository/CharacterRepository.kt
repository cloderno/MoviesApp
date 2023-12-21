package com.example.moviesapp.domain.repository

import com.example.moviesapp.domain.model.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun getCharacterById(id: Int): Character
}