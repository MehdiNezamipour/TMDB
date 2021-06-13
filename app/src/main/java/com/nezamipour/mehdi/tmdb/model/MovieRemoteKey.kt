package com.nezamipour.mehdi.tmdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieRemoteKey")
data class MovieRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nextKey: Int?,
    val prevKey: Int?
)
