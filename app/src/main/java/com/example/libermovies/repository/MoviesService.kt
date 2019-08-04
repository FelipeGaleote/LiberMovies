package com.example.libermovies.repository

import com.example.libermovies.repository.result.MoviesSearchResult
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Single

interface MoviesService {

    @GET("?apikey=e3d4a78c&plot=full")
    fun searchMovies(@Query("s") title : String, @Query("page") page : String) : Single<MoviesSearchResult>
}