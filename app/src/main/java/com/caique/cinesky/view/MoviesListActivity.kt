package com.caique.cinesky.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Toast
import com.caique.cinesky.R
import com.caique.cinesky.adapter.MoviesRecyclerViewAdapter
import com.caique.cinesky.model.MoviesResponse
import com.caique.cinesky.utils.Constants.Companion.ERROR_ANIMATION_SUZE
import com.caique.cinesky.utils.Constants.Companion.MOVIE_PARCELABLE_KEY
import com.caique.cinesky.viewmodel.MoviesListViewModel
import kotlinx.android.synthetic.main.movies_list_activity.*
import kotlinx.android.synthetic.main.toolbar.*

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
                onSuccess(it)
            } else {
                onFailure()
            }
        })
    }

    private fun onSuccess(it: ArrayList<MoviesResponse>) {
        loading.visibility = View.GONE
        tv_list_description.visibility = View.VISIBLE
        setRecyclerViewAdapter(it)
    }

    private fun onFailure() {
        loading.layoutParams.width = ERROR_ANIMATION_SUZE
        loading.layoutParams.height = ERROR_ANIMATION_SUZE
        loading.setAnimation("404.json")
        loading.playAnimation()
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
            val intent = Intent(this, MovieInfoActivity::class.java)
            intent.putExtra(MOVIE_PARCELABLE_KEY, it)
            startActivity(intent)
        }
    }

    private fun backPressed() {
        bt_back.setOnClickListener {
            finish()
        }
    }

}
