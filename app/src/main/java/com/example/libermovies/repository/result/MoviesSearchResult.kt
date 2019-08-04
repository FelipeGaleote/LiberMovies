package com.example.libermovies.repository.result

import com.example.libermovies.model.Movie
import com.google.gson.annotations.SerializedName

class MoviesSearchResult (@SerializedName("Search")
                          val movies: List<Movie>,
                          @SerializedName("totalResults")
                          val totalResults: String,
                          @SerializedName("Response")
                          val response: String)