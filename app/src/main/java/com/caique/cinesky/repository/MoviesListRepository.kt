package com.caique.cinesky.repository

import android.arch.lifecycle.MutableLiveData
import com.caique.cinesky.data.remote.CallApi
import com.caique.cinesky.data.remote.RetrofitClientInstance
import com.caique.cinesky.model.MoviesResponse
import retrofit2.Call
import retrofit2.Response

class MoviesListRepository  {
    private val service: CallApi = RetrofitClientInstance.getRetrofitInstance()

    fun getMoviesList() : MutableLiveData<ArrayList<MoviesResponse>> {
        val moviesResponseMutableLiveData: MutableLiveData<ArrayList<MoviesResponse>> = MutableLiveData()
        service.getMovies().enqueue(object : retrofit2.Callback<ArrayList<MoviesResponse>> {
            override fun onFailure(call: Call<ArrayList<MoviesResponse>>, t: Throwable) {
                moviesResponseMutableLiveData.postValue(null)
            }

            override fun onResponse(call: Call<ArrayList<MoviesResponse>>,
                                    response: Response<ArrayList<MoviesResponse>>) {
               moviesResponseMutableLiveData.postValue(response.body())
            }

        })
        return moviesResponseMutableLiveData
    }

    object MoviesListRepositoryProvider {
        fun provideMovieListRepository() : MoviesListRepository {
            return MoviesListRepository()
        }
    }
}