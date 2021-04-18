package com.nezamipour.mehdi.tmdb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nezamipour.mehdi.moviebaz.data.model.Movie
import com.nezamipour.mehdi.tmdb.databinding.MovieItemLayoutBinding
import com.nezamipour.mehdi.tmdb.utils.ImageUtils

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListHolder>() {


    private lateinit var movies: List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListHolder {
        val binding =
            MovieItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MovieListHolder(private val binding: MovieItemLayoutBinding) :

        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.iv.apply {
                Glide.with(binding.root)
                    .load(movie.posterPath?.let { ImageUtils.getImageUrl(it) })
                    .into(this)

            }
            binding.tvMovieTitle.text = movie.title
        }
    }

}