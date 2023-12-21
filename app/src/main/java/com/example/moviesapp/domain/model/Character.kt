package com.example.moviesapp.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val origin: Origin,
    val location: Location,
    val image: String,
    val episode: List<String>,
    val url: String
) {
    fun getFormattedStatus(): String {
        return status.name.toLowerCase().capitalize();
    }
}

data class Location(
    val name: String,
    val url: String
)

data class Origin(
    val name: String,
    val url: String
)
enum class CharacterStatus {
    ALIVE, DEAD, UNKNOWN
}

enum class CharacterGender {
    FEMALE, MALE, GENDERLESS, UNKNOWN
}