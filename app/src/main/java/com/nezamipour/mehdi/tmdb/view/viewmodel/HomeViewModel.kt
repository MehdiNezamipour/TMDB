package com.nezamipour.mehdi.tmdb.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.nezamipour.mehdi.tmdb.data.local.AppDatabase
import com.nezamipour.mehdi.tmdb.data.local.MovieDao
import com.nezamipour.mehdi.tmdb.data.local.MovieRemoteKeyDao
import com.nezamipour.mehdi.tmdb.data.remote.ApiService
import com.nezamipour.mehdi.tmdb.paging.MovieRemoteMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) :
    ViewModel() {


    @ExperimentalPagingApi
    val pager = Pager(
        PagingConfig(pageSize = 20,2,true),
        remoteMediator = MovieRemoteMediator(apiService = apiService, appDatabase = appDatabase)
    ) {
        appDatabase.getMovieDao().pagingSource()
    }.flow

}