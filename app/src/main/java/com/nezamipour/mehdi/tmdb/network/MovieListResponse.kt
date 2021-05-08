package com.nezamipour.mehdi.tmdb.network

import com.google.gson.annotations.SerializedName
import com.nezamipour.mehdi.tmdb.data.model.Movie

class MovieListResponse {
    @SerializedName("total_pages")
    val totalPages: Int? = null

    @SerializedName("total_results")
    val totalResults: Int? = null

    @SerializedName("results")
    val results: List<Movie>? = null

    @SerializedName("page")
    val page: Int? = null
}