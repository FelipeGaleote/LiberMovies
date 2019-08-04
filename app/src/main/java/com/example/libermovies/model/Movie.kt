package com.example.libermovies.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbRating")
    val imdbRating: String,
    @SerializedName("Poster")
    val posterUrl: String
)