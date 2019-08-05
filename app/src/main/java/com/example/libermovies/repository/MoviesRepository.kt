package com.example.libermovies.repository

import com.example.libermovies.model.Movie
import com.example.libermovies.repository.result.MoviesSearchResult
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MoviesRepository {

    fun searchMovies(title: String, page: String) : Single<MoviesSearchResult> {
        return newRetrofitInstance().create<MoviesDataSource>(MoviesDataSource::class.java)
            .searchMovies(title, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getMovieDetails(imdbId: String) : Single<Movie> {
        return newRetrofitInstance().create<MoviesDataSource>(MoviesDataSource::class.java)
            .getMovieDetails(imdbId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}