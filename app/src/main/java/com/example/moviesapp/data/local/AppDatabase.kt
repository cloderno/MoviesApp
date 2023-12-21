package com.example.moviesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesapp.data.local.dao.CharacterDao

@Database(entities = [Character::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}