package com.nezamipour.mehdi.tmdb.view.adapter


import android.os.Bundle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nezamipour.mehdi.tmdb.R
import com.nezamipour.mehdi.tmdb.databinding.MovieItemLayoutBinding
import com.nezamipour.mehdi.tmdb.model.Movie
import com.nezamipour.mehdi.tmdb.utils.ImageUtils


class MovieHolder(
    private val binding: MovieItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {


    var movie: Movie? = null

    fun bind(movie: Movie) {
        this.movie = movie
        // TODO Later
        //setListeners(movieId = movie.id)
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
