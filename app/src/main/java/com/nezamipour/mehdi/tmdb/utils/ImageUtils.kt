package com.nezamipour.mehdi.tmdb.utils

class ImageUtils {

    // w = width
    companion object {
        fun getImageUrl(imagePath: String) = "https://image.tmdb.org/t/p/w200$imagePath"

        fun getLargeImageUrl(imagePath: String) = "https://image.tmdb.org/t/p/w500$imagePath"
    }

}