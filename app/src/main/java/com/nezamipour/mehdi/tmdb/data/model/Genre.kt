package com.nezamipour.mehdi.tmdb.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    var id: Int? = null,
    var name: String? = null,

    ) : Parcelable