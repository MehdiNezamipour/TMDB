package com.nezamipour.mehdi.tmdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nezamipour.mehdi.tmdb.model.Genre
import com.nezamipour.mehdi.tmdb.model.Movie
import com.nezamipour.mehdi.tmdb.model.MovieRemoteKey

@Database(
    entities = [Movie::class, Genre::class, MovieRemoteKey::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getMovieRemoteKeyDao(): MovieRemoteKeyDao

}