package com.example.libermovies.repository

import com.example.libermovies.repository.result.MoviesSearchResult
import rx.Single
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MoviesRepository {

    fun searchMovies(title: String, page: String) : Single<MoviesSearchResult> {
        return newRetrofitInstance().create<MoviesService>(MoviesService::class.java)
            .searchMovies(title, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}