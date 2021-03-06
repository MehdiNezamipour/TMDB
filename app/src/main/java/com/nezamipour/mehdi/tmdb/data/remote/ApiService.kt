package com.nezamipour.mehdi.tmdb.data.remote

import androidx.paging.PagingSource
import androidx.room.Insert
import com.nezamipour.mehdi.tmdb.model.Genre
import com.nezamipour.mehdi.tmdb.model.Movie
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    // only we work with this request in this app
    @GET(Routes.POPULAR)
    suspend fun getPopular(
        @Query("page") page: Int
    ): Response<MovieListResponse>


    @GET(Routes.MOVIE_DETAIL)
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int
    ): Response<Movie>


    @GET(Routes.SEARCH)
    suspend fun searchMovie(
        @Query("query") searchQuery: String
    ): Response<List<Movie>>

    @GET(Routes.MOVIE_DISCOVER)
    suspend fun discoverByGenre(
        @Query("with_genres") genres: String,
        @Query("page") page: Int
    ): Response<List<Movie>>


    //get all genres
    @GET(Routes.MOVIE_GENRES)
    suspend fun getAllGenres(): Response<List<Genre>>

}