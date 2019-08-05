package com.example.libermovies.screen.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.libermovies.R
import com.example.libermovies.databinding.ActivityMovieDetailsBinding
import com.example.libermovies.model.Movie
import com.example.libermovies.screen.view_model.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel

    companion object {
        const val IMDB_ID_KEY = "IMDB_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()

        val imdbKey = intent.getStringExtra(IMDB_ID_KEY)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)
        movieDetailsViewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)

        setupObservers()
        movieDetailsViewModel.loadMovieDetails(imdbKey)
    }

    private fun setupObservers() {
        movieDetailsViewModel.movieResponse.observe(this, Observer { movie ->
            run {
                if (movie == null) {
                    renderFailedRetrievingMovie()
                } else {
                    renderMovie(movie)
                }
            }
        })

    }

    private fun setupActionBar() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return false
    }

    private fun renderMovie(movie: Movie) {
        binding.movieTitle.text = movie.title
        Glide.with(binding.movieBanner)
            .load(movie.posterUrl)
            .apply(RequestOptions().placeholder(R.drawable.ic_movie))
            .into(binding.movieBanner)
        binding.movieDuration.text = movie.runtime
        binding.movieImdbRating.text = movie.imdbRating
        binding.movieLanguage.text = movie.language
        binding.movieYear.text = movie.year
        binding.moviePlot.text = movie.plot
    }

    private fun renderFailedRetrievingMovie() {

    }
}