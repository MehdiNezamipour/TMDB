package com.nezamipour.mehdi.tmdb.view.adapter


import androidx.recyclerview.widget.RecyclerView
import com.nezamipour.mehdi.tmdb.databinding.MovieItemLayoutBinding
import com.nezamipour.mehdi.tmdb.model.Movie


class MovieHolder(
    private val binding: MovieItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {


    var movie: Movie? = null

    fun bind(movie: Movie) {
        this.movie = movie

    }


}
