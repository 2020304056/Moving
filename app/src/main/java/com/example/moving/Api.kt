package com.example.moving

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page: Int
    ): Call<GetMoviesResponse>
}