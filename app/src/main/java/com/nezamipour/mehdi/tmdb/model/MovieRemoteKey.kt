package com.nezamipour.mehdi.tmdb.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieRemoteKey")
data class MovieRemoteKey(
    val nextKey: Int?,
    val prevKey: Int?
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1
}
