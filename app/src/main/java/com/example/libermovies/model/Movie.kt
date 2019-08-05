package com.example.libermovies.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title")
    val title: String,
    @SerializedName("imdbID")
    val imdbId: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("imdbRating")
    val imdbRating: String,
    @SerializedName("Poster")
    val posterUrl: String,
    @SerializedName("Runtime")
    val runtime: String,
    @SerializedName("Plot")
    val plot: String,
    @SerializedName("Language")
    val language: String,
    @SerializedName("Country")
    val country: String,
    @SerializedName("Response")
    val response: String
)