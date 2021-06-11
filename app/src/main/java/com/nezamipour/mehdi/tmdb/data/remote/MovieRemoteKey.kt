package com.nezamipour.mehdi.tmdb.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieRemoteKey")
data class MovieRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val next: Int?,
    val prev: Int?
)
