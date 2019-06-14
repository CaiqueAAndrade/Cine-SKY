package com.caique.cinesky.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.Toast
import com.caique.cinesky.R
import com.caique.cinesky.adapter.MoviesRecyclerViewAdapter
import com.caique.cinesky.model.MoviesResponse
import com.caique.cinesky.viewmodel.MoviesListViewModel
import kotlinx.android.synthetic.main.movies_list_activity.*

class MoviesListActivity : AppCompatActivity() {

    private lateinit var moviesListViewModel: MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movies_list_activity)

        moviesListViewModel = ViewModelProviders.of(this)[MoviesListViewModel::class.java]

        setupUi()
    }

    private fun setupUi() {
        getMoviesResponse()
        backPressed()
    }

    private fun getMoviesResponse() {
        moviesListViewModel.getMoviesListRepository().observe(this, Observer {
            if (it != null) {
                setRecyclerViewAdapter(it)
            }
        })
    }

    private fun setRecyclerViewAdapter(moviesList: ArrayList<MoviesResponse>) {
        val recyclerView = movies_recyclerview
        val moviesAdapter = MoviesRecyclerViewAdapter(this, moviesList)
        recyclerView.adapter = moviesAdapter
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        onItemClick(moviesAdapter)
    }

    private fun onItemClick(moviesAdapter: MoviesRecyclerViewAdapter) {
        moviesAdapter.onItemClick = {
            Toast.makeText(this, it.title + "", Toast.LENGTH_LONG).show()
        }
    }

    private fun backPressed() {
        bt_back.setOnClickListener {
            finish()
        }
    }

}
