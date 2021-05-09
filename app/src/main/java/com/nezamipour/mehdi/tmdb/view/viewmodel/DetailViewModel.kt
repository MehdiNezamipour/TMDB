package com.nezamipour.mehdi.tmdb.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nezamipour.mehdi.tmdb.data.model.Movie
import com.nezamipour.mehdi.tmdb.data.model.repository.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    val movieDetails: LiveData<Movie> = movieRepository.movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieRepository.loadDetails(movieId)
        }
    }
}