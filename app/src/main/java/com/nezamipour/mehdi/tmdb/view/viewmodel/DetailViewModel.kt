package com.nezamipour.mehdi.tmdb.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nezamipour.mehdi.tmdb.model.Movie
import com.nezamipour.mehdi.tmdb.repository.MovieRepository
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    val movieDetails: LiveData<Movie> = movieRepository.movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieRepository.loadDetails(movieId)
        }
    }
}