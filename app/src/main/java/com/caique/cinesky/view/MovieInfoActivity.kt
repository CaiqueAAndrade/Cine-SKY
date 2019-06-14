package com.caique.cinesky.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.caique.cinesky.R
import com.caique.cinesky.adapter.BackdropsRecyclerViewAdapter
import com.caique.cinesky.databinding.MovieInfoActivityBinding
import com.caique.cinesky.model.MoviesResponse
import com.caique.cinesky.utils.Constants.Companion.MOVIE_PARCELABLE_KEY
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.toolbar.*

class MovieInfoActivity : AppCompatActivity() {

    lateinit var movieInfoActivityBinding: MovieInfoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_info_activity)

        val movieInfo: MoviesResponse = intent.getSerializableExtra(MOVIE_PARCELABLE_KEY) as MoviesResponse
        setupUi(movieInfo)
    }

    private fun setupUi(movieInfo: MoviesResponse) {
        setupDataOnView(movieInfo)
        backPressed()
        setupAdapter(movieInfo.backdropsUrl)
        setupBackdropImageClickListener()
    }

    private fun setupDataOnView(movieInfo: MoviesResponse) {
        movieInfoActivityBinding = DataBindingUtil.setContentView(this, R.layout.movie_info_activity)
        movieInfoActivityBinding.setVariable(1, movieInfo)
        movieInfoActivityBinding.executePendingBindings()
        Picasso.get().load(movieInfo.coverUrl).error(R.drawable.no_poster).into(movieInfoActivityBinding.coverImage)
    }

    private fun setupAdapter(backdrops: List<String>) {
        val recyclerView = movieInfoActivityBinding.movieBackdropRecyclerview
        val backdropsAdapter = BackdropsRecyclerViewAdapter(this, backdrops)
        recyclerView.adapter = backdropsAdapter
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.layoutManager = layoutManager

        setupBackdropRecyclerViewItemClickListener(backdropsAdapter)
    }

    private fun setupBackdropRecyclerViewItemClickListener(backdropsAdapter: BackdropsRecyclerViewAdapter) {
        backdropsAdapter.onItemClick = {
            movieInfoActivityBinding.backdropImageExpand.visibility = View.VISIBLE
            Picasso.get().load(it).error(R.drawable.image_not_found).into(movieInfoActivityBinding.backdropImage)
        }
    }

    private fun setupBackdropImageClickListener() {
        movieInfoActivityBinding.backdropImageExpand.setOnClickListener {
            it.visibility = View.GONE
        }
    }

    private fun backPressed() {
        bt_back.setOnClickListener {
            finish()
        }
    }
}