package com.caique.cinesky.data.remote

import com.caique.cinesky.model.MoviesResponse
import com.caique.cinesky.utils.Constants.Companion.MOVIES
import retrofit2.Call
import retrofit2.http.GET

interface CallApi {

    @GET(MOVIES)
    fun getMovies() : Call<ArrayList<MoviesResponse>>
}