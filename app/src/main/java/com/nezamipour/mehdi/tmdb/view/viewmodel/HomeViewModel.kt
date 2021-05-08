package com.nezamipour.mehdi.tmdb.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nezamipour.mehdi.tmdb.data.model.Movie
import com.nezamipour.mehdi.tmdb.data.model.repository.MovieRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

    val movies: LiveData<List<Movie>> = movieRepository.movies


    fun fetchPopularMovies(page: Int) {
        viewModelScope.launch {
            movieRepository.loadMovies(page)
        }
    }


}