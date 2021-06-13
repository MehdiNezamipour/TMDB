package com.nezamipour.mehdi.tmdb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nezamipour.mehdi.tmdb.databinding.LoadStateViewBinding

class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LoadStateViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class LoadStateViewHolder(private val binding: LoadStateViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.loadStateRetry.isVisible = loadState !is LoadState.Loading
            binding.loadStateErrorMessage.isVisible = loadState !is LoadState.Loading
            binding.loadStateProgress.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error) {
                binding.loadStateErrorMessage.text = loadState.error.localizedMessage
            }
            /*  binding.loadStateRetry.setOnClickListener {
                  retry.invoke()
              }*/
        }
    }
}