package com.smolianinovasiuzanna.movielist.data

import com.smolianinovasiuzanna.movielist.network.Network
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MovieRepository {

    suspend fun showMovieFeed(pageCount: Int): NyResponse.MovieList {
        return withContext(Dispatchers.IO) {
            Timber.d("showMovieFeed")
            Network.movieApi.fetchMovieFeed(pageCount)
        }
    }
}