package com.nezamipour.mehdi.tmdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nezamipour.mehdi.tmdb.model.Genre
import com.nezamipour.mehdi.tmdb.model.Movie

@Database(entities = arrayOf(Movie::class, Genre::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getMovieRemoteKeyDao(): MovieRemoteKeyDao

}