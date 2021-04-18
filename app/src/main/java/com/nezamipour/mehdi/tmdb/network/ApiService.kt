package com.nezamipour.mehdi.tmdb.network

import com.nezamipour.mehdi.moviebaz.data.model.Genre
import com.nezamipour.mehdi.moviebaz.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    //each page return 10 movies
    @GET(Routes.POPULAR)
    suspend fun getPopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Response<List<Movie>>

    @GET(Routes.MOVIE_DETAIL)
    suspend fun getMovieDetails(
        @Query("api_key") api_key: String,
        @Path("movie_id") movie_id: Int
    ): Response<Movie>


    @GET(Routes.SEARCH)
    suspend fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") searchQuery: String
    ): Response<List<Movie>>

    @GET(Routes.MOVIE_DISCOVER)
    suspend fun discoverByGenre(
        @Query("api_key") api_key: String,
        @Query("with_genres") genres: String,
        @Query("page") page: Int
    ): Response<List<Movie>>


    //get all genres
    @GET(Routes.MOVIE_GENRES)
    suspend fun getAllGenres(@Query("api_key") api_key: String): Response<List<Genre>>

}