package com.smolianinovasiuzanna.movielist.network

import com.smolianinovasiuzanna.movielist.data.NyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("reviews/all.json")
    suspend fun fetchMovieFeed(@Query("offset")pageCount: Int): NyResponse.MovieList
}