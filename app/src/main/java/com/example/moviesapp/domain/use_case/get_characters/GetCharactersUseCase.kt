package com.example.moviesapp.domain.use_case.get_characters


import com.example.moviesapp.data.remote.dto.character.toDomainModel
import com.example.moviesapp.domain.model.Character
import com.example.moviesapp.domain.repository.CharacterRepository
import com.example.moviesapp.helpers.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
){
    operator fun invoke(): Flow<Resource<List<Character>>> = flow {
        try {
            emit(Resource.Loading())
            val characters =  repository.getCharacters().map { it.toDomainModel() }
            emit(Resource.Success(characters))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Network error occurred. Please check your connection."))
        }
    }
}