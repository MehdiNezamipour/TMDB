package com.nezamipour.mehdi.tmdb.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.nezamipour.mehdi.tmdb.model.Movie

@Dao
interface MovieDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movie ORDER BY voteAverage DESC")
    fun pagingSource(): PagingSource<Int, Movie>

    @Query("DELETE FROM movie ")
    suspend fun deleteAll()

    @Update
    suspend fun updateMovie(movie: Movie)


}