package com.nezamipour.mehdi.moviebaz.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    var id: Int? = null,

    var title: String? = null,

    var overview: String? = null,

    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("vote_count")
    var voteCount: Int? = null,

    @SerializedName("vote_average")
    var voteAverage: Float? = null,

    var popularity: Float? = null,

    @SerializedName("original_language")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    var adult: Boolean? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null

) : Parcelable