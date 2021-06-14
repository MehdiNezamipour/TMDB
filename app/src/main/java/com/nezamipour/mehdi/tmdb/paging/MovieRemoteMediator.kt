package com.nezamipour.mehdi.tmdb.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.nezamipour.mehdi.tmdb.data.local.AppDatabase
import com.nezamipour.mehdi.tmdb.data.remote.ApiService
import com.nezamipour.mehdi.tmdb.model.Movie
import com.nezamipour.mehdi.tmdb.model.MovieRemoteKey
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService,
    private val initialPage: Int = 1
) : RemoteMediator<Int, Movie>() {
    private val movieDao = appDatabase.getMovieDao()
    private val movieRemoteKeyDao = appDatabase.getMovieRemoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {

        return try {
            // The network load method takes an optional String
            // parameter. For every page after the first, pass the String
            // token returned from the previous page to let it continue
            // from where it left off. For REFRESH, pass null to load the
            // first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> initialPage
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                // Query remoteKeyDao for the next RemoteKey.
                LoadType.APPEND -> {
                    val remoteKey = appDatabase.withTransaction {
                        movieRemoteKeyDao.getRemoteKey()
                    }

                    // You must explicitly check if the page key is null when
                    // appending, since null is only valid for initial load.
                    // If you receive null for APPEND, that means you have
                    // reached the end of pagination and there are no more
                    // items to load.
                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    remoteKey.nextKey
                }
            }

            // Suspending network load via Retrofit. This doesn't need to
            // be wrapped in a withContext(Dispatcher.IO) { ... } block
            // since Retrofit's Coroutine CallAdapter dispatches on a
            // worker thread.
            val response = loadKey.let { apiService.getPopular(it) }
            val endOfPaginationReached = response.body()?.results!!.isEmpty()

            // Store loaded data, and next key in transaction, so that
            // they're always consistent.
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    movieRemoteKeyDao.deleteAll()
                    movieDao.deleteAll()
                }

                val prevKey = if (loadKey == 1) null else loadKey.minus(1)
                val nextKey = if (endOfPaginationReached) null else loadKey.plus(1)

                // Update RemoteKey for this query.
                movieRemoteKeyDao.insertOrReplace(
                    MovieRemoteKey(prevKey = prevKey, nextKey = nextKey)
                )

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                movieDao.insertAll(response.body()!!.results)
            }

            MediatorResult.Success(
                endOfPaginationReached = endOfPaginationReached
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}