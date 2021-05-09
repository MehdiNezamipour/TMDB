package com.nezamipour.mehdi.tmdb.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nezamipour.mehdi.tmdb.R
import com.nezamipour.mehdi.tmdb.data.model.Movie
import com.nezamipour.mehdi.tmdb.databinding.MovieItemLayoutBinding
import com.nezamipour.mehdi.tmdb.utils.ImageUtils
import javax.inject.Inject

class MovieListAdapter @Inject constructor() :
    RecyclerView.Adapter<MovieListAdapter.MovieListHolder>() {


    val movies = ArrayList<Movie>()

    fun setMovieList(list: List<Movie>) {
        movies.clear()
        movies.addAll(list)
        notifyDataSetChanged()
    }


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
            movie.id?.let { setListeners(movieId = it) }
            binding.textViewDescription.text = movie.overview
            binding.ratingBar.rating = movie.voteAverage!! / 2
            binding.textViewRating.text = movie.voteAverage.toString()
            binding.imageViewMovie.apply {
                Glide.with(binding.root)
                    .load(movie.posterPath?.let { ImageUtils.getImageUrl(it) })
                    .into(this)

            }
            binding.textViewTitle.text = movie.title
        }

        private fun setListeners(movieId: Int) {
            binding.root.setOnClickListener {
                val args = Bundle()
                args.putInt("movieId", movieId)
                Navigation.findNavController(it)
                    .navigate(R.id.action_homeFragment_to_detailFragment, args)
            }
        }
    }

}