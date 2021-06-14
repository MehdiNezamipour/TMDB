package com.nezamipour.mehdi.tmdb.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel

@Entity(tableName = "movie")
@Parcelize
data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
/*    @SerializedName("genre_ids")
    val genreIds: List<Int>?,*/
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("vote_count")
    val voteCount: Int?,
    @SerializedName("vote_average")
    val voteAverage: Float?,
    val popularity: Float?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val adult: Boolean?,
    @SerializedName("release_date")
    val releaseDate: String?

) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idd")
    var idd: Int = 1
}