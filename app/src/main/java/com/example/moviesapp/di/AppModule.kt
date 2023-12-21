// app/src/main/java/com/example/moviesapp/di/AppModule.kt
package com.example.moviesapp.di

import com.example.moviesapp.domain.repository.CharacterRepository
import com.example.moviesapp.data.remote.dto.character.CharacterService
import com.example.moviesapp.data.repository.CharacterRepositoryImpl
import com.example.moviesapp.utils.constants.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterService(okHttpClient: OkHttpClient): CharacterService {
        val gson: Gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(CharacterService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(characterService: CharacterService): CharacterRepository {
        return CharacterRepositoryImpl(characterService)
    }
}
