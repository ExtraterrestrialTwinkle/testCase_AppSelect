package com.smolianinovasiuzanna.movielist.network

import com.smolianinovasiuzanna.movielist.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

object Network {

    private val authInterceptor = Interceptor { chain ->
        val request: Request = chain.request()
        val newUrl = request.url.newBuilder()
            .addQueryParameter("api-key", BuildConfig.API_KEY)
            .build()
        val newRequest = chain.request().newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val okhttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(authInterceptor)
        .addNetworkInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.nytimes.com/svc/movies/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okhttpClient)
        .build()

    val movieApi: Api
        get() = retrofit.create()
}