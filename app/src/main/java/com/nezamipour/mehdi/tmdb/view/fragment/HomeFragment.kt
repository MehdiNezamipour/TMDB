package com.nezamipour.mehdi.tmdb.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.nezamipour.mehdi.tmdb.databinding.FragmentHomeBinding
import com.nezamipour.mehdi.tmdb.view.adapter.MoviePagingAdapter
import com.nezamipour.mehdi.tmdb.view.adapter.PagingLoadStateAdapter
import com.nezamipour.mehdi.tmdb.view.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var pagingAdapter: MoviePagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(pagingAdapter) {
            binding.recyclerView.setHasFixedSize(true)
            binding.recyclerView.adapter = this.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.pager.collectLatest {
                    pagingAdapter.submitData(it)
                }
            }

            //TODO LATER FIX BUG
//            binding.swipeRefresher.setOnRefreshListener {
//                pagingAdapter.refresh()
//            }
//
//            viewLifecycleOwner.lifecycleScope.launch {
//                loadStateFlow.collectLatest {
//                    binding.swipeRefresher.isRefreshing = it.refresh is LoadState.Loading
//                }
//            }
//
//            viewLifecycleOwner.lifecycleScope.launch {
//                loadStateFlow.distinctUntilChangedBy { it.refresh }
//                    .filter { it.refresh is LoadState.NotLoading }
//                    .collect { binding.recyclerView.scrollToPosition(0) }
//            }
        }


    }

}