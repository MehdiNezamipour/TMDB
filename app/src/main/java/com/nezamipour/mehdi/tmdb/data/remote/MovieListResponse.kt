package com.nezamipour.mehdi.tmdb.data.remote

import com.google.gson.annotations.SerializedName
import com.nezamipour.mehdi.tmdb.model.Movie

data class MovieListResponse(
    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("total_results")
    val totalResults: Int?,

    @SerializedName("results")
    val results: List<Movie>?,

    @SerializedName("page")
    val page: Int?
) {

}