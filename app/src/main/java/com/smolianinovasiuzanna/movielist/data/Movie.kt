package com.smolianinovasiuzanna.movielist.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

sealed class NyResponse {

    @JsonClass(generateAdapter = true)
    data class MovieList(
        @Json(name = "results") val list: List<Movie>
    )

    @JsonClass(generateAdapter = true)
    data class Movie(
        @Json(name = "display_title") val title: String,
        @Json(name = "summary_short") val description: String,
        @Json(name = "multimedia") val preview: Multimedia
    )

    @JsonClass(generateAdapter = true)
    data class Multimedia(
        @Json(name = "src") val previewLink: String
    )
}


