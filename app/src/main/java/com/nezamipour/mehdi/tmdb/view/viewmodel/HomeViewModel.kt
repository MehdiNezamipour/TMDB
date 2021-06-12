package com.nezamipour.mehdi.tmdb.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.nezamipour.mehdi.tmdb.data.local.MovieDao
import com.nezamipour.mehdi.tmdb.data.local.MovieRemoteKeyDao
import com.nezamipour.mehdi.tmdb.data.remote.ApiService
import com.nezamipour.mehdi.tmdb.paging.MovieRemoteMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    apiService: ApiService,
    private val movieDao: MovieDao,
    movieRemoteKeyDao: MovieRemoteKeyDao
) :
    ViewModel() {

    private val pagingConfig =
        PagingConfig(pageSize = 20)

    @ExperimentalPagingApi
    val pager =
        Pager(
            pagingConfig,
            remoteMediator = MovieRemoteMediator(apiService, movieDao, movieRemoteKeyDao,1),
        ) {
            movieDao.pagingSource()
        }.flow


}