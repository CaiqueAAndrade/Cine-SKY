package com.caique.cinesky.data.remote

import com.caique.cinesky.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface CallApi {

    @GET("Movies")
    fun getMovies() : Call<ArrayList<MoviesResponse>>
}