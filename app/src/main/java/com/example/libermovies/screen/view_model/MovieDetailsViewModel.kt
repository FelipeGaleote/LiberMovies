package com.example.libermovies.screen.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.libermovies.model.Movie
import com.example.libermovies.repository.MoviesRepository

class MovieDetailsViewModel : ViewModel() {

    var movieResponse: MutableLiveData<Movie?> = MutableLiveData()


    fun loadMovieDetails(imdbId: String) {
            val moviesRepository = MoviesRepository()
            moviesRepository.getMovieDetails(imdbId)
                .doOnError { throwable ->
                    throwable.printStackTrace()
                }
                .subscribe ( { movie ->
                    if (movie.response != "False") {
                        movieResponse.value = movie
                    }
                }, {error ->
                    error.printStackTrace()
                })
        }
}