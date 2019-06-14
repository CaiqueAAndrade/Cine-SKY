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
import kotlinx.android.synthetic.main.movie_backdrop_item.view.*
import kotlinx.android.synthetic.main.movie_list_item.view.*
import kotlinx.android.synthetic.main.toolbar.*

class BackdropsRecyclerViewAdapter(val context: Context, val backdrops: List<String>) :
    RecyclerView.Adapter<BackdropsRecyclerViewAdapter.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_backdrop_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return backdrops.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val backdrops = backdrops[p1]
        Picasso.get().load(backdrops).error(R.drawable.image_not_found).into(p0.image)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.backdrop_image

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(backdrops[adapterPosition])
            }
        }
    }

}