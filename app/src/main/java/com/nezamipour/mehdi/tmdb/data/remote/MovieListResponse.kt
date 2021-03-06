package com.nezamipour.mehdi.tmdb.data.remote

import com.nezamipour.mehdi.tmdb.model.Movie

data class MovieListResponse(
    val results: List<Movie>,
    val page: Int? = null
)