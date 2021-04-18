package com.nezamipour.mehdi.tmdb.network

object Routes {

    //for implement paging for recyclerView
    // key = page , value = integer Specify which page to query.
    // when use GET popular movies

    const val API_KEY = "7c2506e17b550d1717df59024d8107ca"

    const val BASE_URL = "https://api.themoviedb.org/"

    const val POPULAR = "/3/movie/popular"

    const val TOP_RATING = "/3/movie/top_rated"

    const val LATEST = "/3/movie/latest"

    const val UPCOMING = "/3/movie/upcoming"

    const val MOVIE_GENRES = "/3/genre/movie/list"

    const val RATE_MOVIE = "/3/movie/{movie_id}/rating"

    const val MOVIE_DETAIL = "/3/movie/{movie_id}"

    //useful queries
    // key= with_genres  value = string Comma separated value of genre ids that you want to include in the results
    const val MOVIE_DISCOVER = "/3/discover/movie"

    // useful queries
    // key = query , value = string Pass a text query to search. This value should be URI encoded
    const val SEARCH = "/3/search/movie"

    const val CONFIGURATION = "/3/configuration"


}