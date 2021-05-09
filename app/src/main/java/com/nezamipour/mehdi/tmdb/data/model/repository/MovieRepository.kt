package com.nezamipour.mehdi.tmdb.data.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nezamipour.mehdi.tmdb.data.model.Movie
import com.nezamipour.mehdi.tmdb.network.ApiService
import com.nezamipour.mehdi.tmdb.network.Routes
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {

    val movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieDetails: MutableLiveData<Movie> = MutableLiveData()

    suspend fun loadMovies(page: Int) {
        val response = apiService.getPopular(Routes.API_KEY, page)
        try {
            if (response.isSuccessful) {
                Log.d("HomeViewModel", "loadMovies:  ${response.body()}")
                movies.value = response.body()?.results
            } else {
                Log.d("HomeViewModel", "getPopularMovies: ${response.errorBody()}")
            }
        } catch (e: HttpException) {
            Log.d("HomeViewModel", "fetchPopularMovies: ${e.message()}")
        }
    }


    suspend fun loadDetails(movieId: Int) {
        val response = apiService.getMovieDetails(movieId,Routes.API_KEY)
        try {
            if (response.isSuccessful) {
                Log.d("HomeViewModel", "loadDetails:  ${response.body()}")
                movieDetails.value = response.body()
            } else {
                Log.d("HomeViewModel", "loadDetails: ${response.errorBody()}")
            }
        } catch (e: HttpException){
            Log.d("HomeViewModel", "loadDetails: ${e.message()}")

        }
    }
}