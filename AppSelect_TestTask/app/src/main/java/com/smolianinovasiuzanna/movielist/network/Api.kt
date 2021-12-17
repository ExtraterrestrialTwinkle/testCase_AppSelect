package com.smolianinovasiuzanna.movielist.network

import com.smolianinovasiuzanna.movielist.data.NyResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("reviews/all.json")
    suspend fun fetchMovieFeed(): NyResponse.MovieList
}