package com.example.libermovies.repository

import com.example.libermovies.model.Movie
import com.example.libermovies.repository.result.MoviesSearchResult
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

interface MoviesDataSource {

    @GET("?apikey=e3d4a78c&type=movie")
    fun searchMovies(@Query("s") title : String, @Query("page") page : String) : Single<MoviesSearchResult>

    @GET("?apikey=e3d4a78c&plot=full")
    fun getMovieDetails(@Query("i") imdbId : String) : Single<Movie>
}