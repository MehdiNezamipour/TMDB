package com.nezamipour.mehdi.tmdb.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nezamipour.mehdi.tmdb.model.Movie
import com.nezamipour.mehdi.tmdb.repository.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    val movies: LiveData<List<Movie>> = movieRepository.movies


    fun fetchPopularMovies(page: Int) {
        viewModelScope.launch {
            movieRepository.loadMovies(page)
        }
    }


}