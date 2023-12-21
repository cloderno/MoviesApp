package com.example.moviesapp.data.remote.dto.character

import com.example.moviesapp.domain.model.Character

data class CharacterDTO(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginDTO,
    val location: LocationDTO,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class LocationDTO(
    val name: String,
    val url: String
)

data class OriginDTO(
    val name: String,
    val url: String
)