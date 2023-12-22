package com.example.moviesapp.data.repository

import com.example.moviesapp.data.remote.dto.character.CharacterDTO
import com.example.moviesapp.data.remote.dto.character.CharacterService
import com.example.moviesapp.data.remote.dto.character.CharactersResponse
import com.example.moviesapp.data.remote.dto.character.toDomainModel
import com.example.moviesapp.domain.model.Character
import com.example.moviesapp.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService
): CharacterRepository {
    override suspend fun getCharacters(): List<CharacterDTO> {
        return characterService.getCharacters().results
    }

    override suspend fun getCharacterById(id: Int): CharacterDTO {
        return characterService.getCharacter(id = id)
    }

}