package com.caique.cinesky.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.caique.cinesky.R
import com.caique.cinesky.viewmodel.MoviesListViewModel

class MoviesListActivity : AppCompatActivity() {

    private lateinit var moviesListViewModel: MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_list_activity)

        moviesListViewModel = ViewModelProviders.of(this)[MoviesListViewModel::class.java]

        getMoviesResponse()
    }

    private fun getMoviesResponse() {
        moviesListViewModel.getMoviesListRepository().observe(this, Observer {

        })
    }
}
