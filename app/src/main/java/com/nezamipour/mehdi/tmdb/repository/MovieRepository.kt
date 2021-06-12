package com.nezamipour.mehdi.tmdb.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nezamipour.mehdi.tmdb.data.remote.ApiService
import com.nezamipour.mehdi.tmdb.data.remote.Routes
import com.nezamipour.mehdi.tmdb.model.Movie
import retrofit2.HttpException
import javax.inject.Inject

class MovieRepository
@Inject constructor(
    private val apiService: ApiService,
) {
    val movieDetails: MutableLiveData<Movie> = MutableLiveData()

    suspend fun loadDetails(movieId: Int) {
        val response = apiService.getMovieDetails(movieId, Routes.API_KEY)
        try {
            if (response.isSuccessful) {
                Log.d("HomeViewModel", "loadDetails:  ${response.body()}")
                movieDetails.value = response.body()
            } else {
                Log.d("HomeViewModel", "loadDetails: ${response.errorBody()}")
            }
        } catch (e: HttpException) {
            Log.d("HomeViewModel", "loadDetails: ${e.message()}")

        }
    }
}