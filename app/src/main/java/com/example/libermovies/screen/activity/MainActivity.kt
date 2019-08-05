package com.example.libermovies.screen.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.libermovies.R
import com.example.libermovies.databinding.ActivityMainBinding
import com.example.libermovies.screen.adapter.ListMoviesAdapter
import com.example.libermovies.screen.listener.LazyScrollListener
import com.example.libermovies.screen.view_model.SearchViewModel

class MainActivity : AppCompatActivity(), ListMoviesAdapter.MovieClickedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var adapter: ListMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        setupRecycler()
        setupSearchBar()

        searchViewModel.moviesResponse.observe(this, Observer { moviesResponse ->
            adapter.setNewMovies(moviesResponse.movies, moviesResponse.searchedText)
        })
    }

    fun setupRecycler() {
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ListMoviesAdapter(this, this)
        binding.moviesRecyclerView.addOnScrollListener(object :
            LazyScrollListener(binding.moviesRecyclerView.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int): Boolean {
                searchViewModel.searchMovies(binding.searchBar.text.toString())
                return false
            }
        })
        binding.moviesRecyclerView.adapter = adapter
    }


    fun setupSearchBar() {
        val handler = Handler()
        var runnable = Runnable { }

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                handler.removeCallbacks(runnable)
                runnable = Runnable {
                    searchViewModel.searchMovies(s.toString())
                }
                handler.postDelayed(runnable, 1000)
            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        binding.btnClearSearchText.setOnClickListener {
            binding.searchBar.setText("")
            adapter.clear()
        }
    }

    override fun movieClicked(imdbId: String) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra(MovieDetailsActivity.IMDB_ID_KEY, imdbId)
        startActivity(intent)
    }
}
