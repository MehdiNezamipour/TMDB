package com.nezamipour.mehdi.tmdb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nezamipour.mehdi.tmdb.data.remote.MovieRemoteKey

@Dao
interface MovieRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieRemoteKey(movieRemoteKey: MovieRemoteKey)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKey(remoteKeys: List<MovieRemoteKey>)

    @Query("SELECT * FROM MovieRemoteKey WHERE id=:id")
    suspend fun getRemoteKey(id: Int): MovieRemoteKey

    @Query("DELETE FROM MovieRemoteKey")
    suspend fun deleteAllRemoteKeys()

}