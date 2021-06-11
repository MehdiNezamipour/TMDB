package com.nezamipour.mehdi.tmdb.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.nezamipour.mehdi.tmdb.model.Movie

@Dao
interface MovieDao {


    @Query("SELECT * FROM movie ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("DELETE FROM movie ")
    suspend fun deleteAll()

    @Update
    suspend fun updateMovie(movie: Movie)


}