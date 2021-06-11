package com.nezamipour.mehdi.tmdb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.nezamipour.mehdi.tmdb.databinding.MovieItemLayoutBinding
import com.nezamipour.mehdi.tmdb.model.Movie
import javax.inject.Inject

class MoviePagingAdapter : PagingDataAdapter<Movie, MovieHolder>(DIFF_CALLBACK) {

    private val items = ArrayList<Movie>()


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }


            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }

    fun setItems(list: List<Movie>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(movie = items[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            MovieItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

}