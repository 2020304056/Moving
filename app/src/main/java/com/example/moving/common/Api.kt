package com.example.moving.common

import com.example.moving.GetTVResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page: Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetMoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetMoviesResponse>

    @GET("discover/movie")
    fun getDiscoverMovies(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetMoviesResponse>

    @GET("tv/popular")
    fun getPopularTV(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetTVResponse>

    @GET("tv/top_rated")
    fun getTopRatedTV(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetTVResponse>

    @GET("tv/on_the_air")
    fun getOnTheAirTV(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetTVResponse>

    @GET("tv/airing_today")
    fun getAiringTodayTV(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetTVResponse>

    @GET("discover/tv")
    fun getDiscoverTV(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetTVResponse>

    @GET("search/movie")
    fun getSearchMovies(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("query")
        query : String,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetMoviesResponse>

    @GET("search/tv")
    fun getSearchTV(
        @Query("api_key")
        apiKey: String = "9897e125444076b7172d70bff4fe9c5d",
        @Query("page")
        page : Int,
        @Query("query")
        query : String,
        @Query("language")
        language : String = "ko,en-US"
    ): Call<GetTVResponse>
}