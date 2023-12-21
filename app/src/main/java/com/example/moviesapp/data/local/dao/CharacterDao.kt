package com.example.moviesapp.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.moviesapp.domain.model.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    suspend fun getCharacters(): List<Character>
    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacterById(id: Int): Character?
    @Query("SELECT * FROM character")
    suspend fun insertCharacters(characters: List<Character>)
}