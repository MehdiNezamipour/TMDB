package com.nezamipour.mehdi.tmdb.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.nezamipour.mehdi.tmdb.data.local.MovieDao
import com.nezamipour.mehdi.tmdb.data.local.MovieRemoteKeyDao
import com.nezamipour.mehdi.tmdb.data.remote.ApiService
import com.nezamipour.mehdi.tmdb.data.remote.Routes
import com.nezamipour.mehdi.tmdb.model.Movie
import com.nezamipour.mehdi.tmdb.model.MovieRemoteKey
import java.io.InvalidObjectException
import javax.inject.Inject

@ExperimentalPagingApi
class MovieRemoteMediator @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val movieRemoteKeyDao: MovieRemoteKeyDao,
    private val initialPage: Int = 1
) : RemoteMediator<Int, Movie>() {


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        return try {
            val page: Int = when (loadType) {
                LoadType.APPEND -> {
                    val remoteKey =
                        getLastRemoteKey(state) ?: throw InvalidObjectException("invalid object")
                    remoteKey.next ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.REFRESH -> {
                    val remoteKey = getClosestRemoteKey(state)
                    remoteKey?.next?.minus(1) ?: initialPage
                }
            }

            // NetWork request
            val response = apiService.getPopular(Routes.API_KEY, page = page)
            val endOfPagination = response.body()?.results!!.size < state.config.pageSize

            if (response.isSuccessful) {
                response.body()?.let { movieListResponse ->
                    if (loadType == LoadType.REFRESH) {
                        movieDao.deleteAll()
                        movieRemoteKeyDao.deleteAllRemoteKeys()
                    }
                    val next = if (endOfPagination) null else page + 1
                    val prev = if (page == initialPage) null else page - 1

                    val remoteList = movieListResponse.results?.map {
                        MovieRemoteKey(id = it.id, next = next, prev = prev)
                    }
                    if (remoteList != null) {
                        movieRemoteKeyDao.insertAllRemoteKey(remoteList)
                    }
                    movieListResponse.results?.let { movieDao.insertAll(it) }
                }
                return MediatorResult.Success(endOfPagination)
            } else {
                return MediatorResult.Success(endOfPaginationReached = true)
            }
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getClosestRemoteKey(state: PagingState<Int, Movie>): MovieRemoteKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let { movie ->
                movieRemoteKeyDao.getRemoteKey(movie.id)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, Movie>): MovieRemoteKey? {
        return state.lastItemOrNull()?.let { movie ->
            movieRemoteKeyDao.getRemoteKey(movie.id)
        }
    }


}