package com.example.moviesample.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MovieApi {
    @GET("/3/movie/now_playing")
    suspend fun getMovies(@QueryMap params:Map<String, String>) : Response<com.example.moviesample.model.Result>
}