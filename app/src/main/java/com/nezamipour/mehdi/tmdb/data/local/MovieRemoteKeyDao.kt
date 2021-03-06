package com.nezamipour.mehdi.tmdb.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nezamipour.mehdi.tmdb.model.MovieRemoteKey

@Dao
interface MovieRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(movieRemoteKey: MovieRemoteKey)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<MovieRemoteKey>)

    @Query("SELECT * FROM MovieRemoteKey ORDER BY id DESC")
    suspend fun getRemoteKey(): MovieRemoteKey

    @Query("DELETE FROM MovieRemoteKey")
    suspend fun deleteAll()

}