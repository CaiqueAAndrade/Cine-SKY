package com.caique.cinesky.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.caique.cinesky.R
import com.caique.cinesky.model.MoviesResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MoviesRecyclerViewAdapter(val context: Context, val moviesList: ArrayList<MoviesResponse>) :
    RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder>() {

    var onItemClick: ((MoviesResponse) -> Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val movie = moviesList[p1]

        p0.title.text = movie.title
        if (movie.coverUrl.isNotEmpty()) Picasso.get().load(movie.coverUrl)
            .error(R.drawable.no_poster).into(p0.image)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.cover_image
        val title: TextView = itemView.title

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(moviesList[adapterPosition])
            }
        }
    }

}