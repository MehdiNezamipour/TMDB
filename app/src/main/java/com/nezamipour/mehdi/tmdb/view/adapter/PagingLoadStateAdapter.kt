package com.nezamipour.mehdi.tmdb.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nezamipour.mehdi.tmdb.R
import com.nezamipour.mehdi.tmdb.databinding.LoadStateViewBinding


class PagingLoadStateAdapter<T : Any, VH : RecyclerView.ViewHolder>(
    private val adapter: PagingDataAdapter<T, VH>
) : LoadStateAdapter<PagingLoadStateAdapter.NetworkStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) =
        NetworkStateItemViewHolder(
            LoadStateViewBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.load_state_view, parent, false)
            )
        ) { adapter.retry() }

    override fun onBindViewHolder(holder: NetworkStateItemViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    class NetworkStateItemViewHolder(
        private val binding: LoadStateViewBinding,
        private val retryCallback: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.loadStateRetry.setOnClickListener { retryCallback() }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                loadStateProgress.isVisible = loadState is LoadState.Loading
                loadStateRetry.isVisible = loadState is LoadState.Error
                loadStateErrorMessage.isVisible =
                    !(loadState as? LoadState.Error)?.error?.message.isNullOrBlank()
                loadStateErrorMessage.text = (loadState as? LoadState.Error)?.error?.message
            }
        }
    }
}