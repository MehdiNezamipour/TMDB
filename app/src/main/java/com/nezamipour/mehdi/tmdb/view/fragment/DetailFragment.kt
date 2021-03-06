package com.nezamipour.mehdi.tmdb.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.nezamipour.mehdi.tmdb.databinding.FragmentDetailBinding
import com.nezamipour.mehdi.tmdb.utils.ImageUtils
import com.nezamipour.mehdi.tmdb.view.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        //MyApplication.getComponent()?.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movieId = arguments?.getInt("movieId")
        if (movieId != null) {
            viewModel.fetchMovieDetails(movieId)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.mainView.visibility = View.GONE
        binding.progressBar.show()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieDetails.observe(viewLifecycleOwner, { it ->


            Glide.with(view).load(it.posterPath?.let {
                ImageUtils.getImageUrl(it)
            }).into(binding.imageViewSmallImage)

            Glide.with(view).load(it.posterPath?.let {

                ImageUtils.getLargeImageUrl(it)

            }).into(binding.imageViewBackGround)

            binding.textViewReleaseData.text = it.releaseDate
            binding.textViewLongDescription.text = it.overview
            binding.ratingBar.rating = it.voteAverage!! / 2
            binding.textViewRating.text = it.voteAverage.toString()

            binding.progressBar.hide()
            binding.mainView.visibility = View.VISIBLE
        })

    }

}