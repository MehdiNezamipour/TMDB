package com.nezamipour.mehdi.tmdb.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.nezamipour.mehdi.tmdb.MyApplication
import com.nezamipour.mehdi.tmdb.R
import com.nezamipour.mehdi.tmdb.databinding.FragmentHomeBinding
import com.nezamipour.mehdi.tmdb.view.adapter.MovieListAdapter
import com.nezamipour.mehdi.tmdb.view.viewmodel.HomeViewModel
import javax.inject.Inject


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var adapter: MovieListAdapter


    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.getComponent()?.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchPopularMovies(1)

        viewModel.movies.observe(this, {
            adapter.setMovieList(it)
        })

    }

    override fun onResume() {
        super.onResume()
        if (adapter.movies.isEmpty()) {
            viewModel.fetchPopularMovies(1)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()

    }


    private fun setAdapter() {
        adapter = MovieListAdapter()
        binding.recyclerView.adapter = adapter
    }

}