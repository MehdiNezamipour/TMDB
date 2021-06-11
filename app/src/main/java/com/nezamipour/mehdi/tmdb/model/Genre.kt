package com.nezamipour.mehdi.tmdb.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "genre")
@Parcelize
data class Genre(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,

    ) : Parcelable