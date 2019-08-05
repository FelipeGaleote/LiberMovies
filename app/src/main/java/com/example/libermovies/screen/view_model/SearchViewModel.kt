package com.example.libermovies.screen.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.libermovies.model.Movie
import com.example.libermovies.repository.MoviesRepository
import com.example.libermovies.repository.result.MoviesSearchResult

class SearchViewModel : ViewModel() {

    var moviesResponse: MutableLiveData<MoviesResponse> = MutableLiveData()
    private var searchMetadata: SearchMetadata

    init {
        searchMetadata = SearchMetadata()
    }

    fun searchMovies(title: String) {

        if (searchMetadata.textSearched != title) {
            val pageIndex = "1"
            val moviesRepository = MoviesRepository()
            moviesRepository.searchMovies(title, pageIndex)
                .doOnError { throwable ->
                    throwable.printStackTrace()
                }
                .subscribe({ moviesSearchResult ->
                    if (moviesSearchResult.response != "False") {
                        searchMetadata =
                            SearchMetadata(title, pageIndex.toInt(), moviesSearchResult.totalResults.toInt())
                        moviesResponse.value = MoviesResponse(moviesSearchResult.movies, title)
                    } else {
                        moviesResponse.value = MoviesResponse(ArrayList(), title)
                        searchMetadata = SearchMetadata(title, 0, 0)
                    }
                }, { error ->
                    error.printStackTrace()
                })
        } else if (searchMetadata.hasNextMoviesPages()) {
            val moviesRepository = MoviesRepository()
            moviesRepository.searchMovies(title, searchMetadata.nextPageIndex())
                .doOnError { throwable ->
                    throwable.printStackTrace()
                }
                .subscribe(
                    { moviesSearchResult ->
                        if (moviesSearchResult.response != "False") {
                            moviesResponse.value = MoviesResponse(moviesSearchResult.movies, title)
                        } else {
                            moviesResponse.value = MoviesResponse(ArrayList(), title)
                        }
                    }, { error ->
                        error.printStackTrace()
                    }
                )
        }
    }


    class SearchMetadata(val textSearched: String, var pageIndex: Int, var totalMoviesNumber: Int) {
        private val pageSize: Int = 10

        constructor() : this("", 0, 0)

        fun hasNextMoviesPages(): Boolean {
            return pageIndex * pageSize < totalMoviesNumber
        }

        fun nextPageIndex(): String {
            pageIndex = ++pageIndex
            return pageIndex.toString()
        }

    }

    data class MoviesResponse(val movies: List<Movie>, val searchedText: String)
}