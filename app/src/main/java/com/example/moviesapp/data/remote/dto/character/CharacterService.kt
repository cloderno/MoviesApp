package com.example.moviesapp.data.remote.dto.character

import com.example.moviesapp.helpers.constants.Endpoints
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET(Endpoints.CHARACTERS)
    suspend fun getCharacters(): CharactersResponse

    @GET(Endpoints.CHARACTER_SINGLE)
    suspend fun getCharacter(@Path("id") id: Int): CharacterDTO
}