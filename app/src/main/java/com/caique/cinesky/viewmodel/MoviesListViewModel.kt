package com.caique.cinesky.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.caique.cinesky.model.MoviesResponse
import com.caique.cinesky.repository.MoviesListRepository

class MoviesListViewModel (application: Application) : AndroidViewModel(application) {

    private val moviesListRepository = MoviesListRepository.MoviesListRepositoryProvider.provideMovieListRepository()

    fun getMoviesListRepository(): LiveData<ArrayList<MoviesResponse>> {
        return moviesListRepository.getMoviesList()
    }
}