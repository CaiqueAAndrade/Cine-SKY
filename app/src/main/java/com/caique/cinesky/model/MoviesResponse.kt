package com.caique.cinesky.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MoviesResponse (@SerializedName("title") val title: String,
                      @SerializedName("overview") val overview: String,
                      @SerializedName("duration") val duration: String,
                      @SerializedName("release_year") val releaseYear: String,
                      @SerializedName("cover_url") val coverUrl: String,
                      @SerializedName("backdrops_url") val backdropsUrl: ArrayList<String>,
                      @SerializedName("id") val id: String) : Serializable