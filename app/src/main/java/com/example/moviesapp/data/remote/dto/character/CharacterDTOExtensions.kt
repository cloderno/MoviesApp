package com.example.moviesapp.data.remote.dto.character

import com.example.moviesapp.domain.model.Character
import com.example.moviesapp.domain.model.CharacterGender
import com.example.moviesapp.domain.model.CharacterStatus
import com.example.moviesapp.domain.model.Location
import com.example.moviesapp.domain.model.Origin

fun CharacterDTO.toDomainModel(): Character {
    return Character(
        id = id,
        name = name,
        status = runCatching { CharacterStatus.valueOf(status.toUpperCase()) }.getOrDefault(CharacterStatus.UNKNOWN),
        species = species,
        type = type,
        gender = runCatching { CharacterGender.valueOf(gender.toUpperCase()) }.getOrDefault(CharacterGender.UNKNOWN),
        origin = origin.toDomainModel(),
        location = location.toDomainModel(),
        image = image,
        episode = episode,
        url = url
    )
}

fun OriginDTO.toDomainModel(): Origin {
    return Origin(name = name, url = url)
}

fun LocationDTO.toDomainModel(): Location {
    return Location(name = name, url = url)
}