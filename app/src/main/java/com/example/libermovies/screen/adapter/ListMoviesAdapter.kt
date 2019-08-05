package com.example.libermovies.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.libermovies.R
import com.example.libermovies.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class ListMoviesAdapter(private var context: Context, private val movieClickedListener: MovieClickedListener) : RecyclerView.Adapter<ListMoviesAdapter.ListMoviesHolder>() {

    interface MovieClickedListener {
        fun movieClicked(imdbId : String)
    }

    var movies : ArrayList<Movie> = ArrayList()
    var searchedText : String = ""

    fun setNewMovies(movies: List<Movie>, searchedText: String) {

        if (this.searchedText == searchedText) {
            this.movies.addAll(movies)
        } else {
            this.movies = ArrayList(movies)
        }
        notifyDataSetChanged()
        this.searchedText = searchedText
    }

    fun clear() {
        this.searchedText = ""
        movies = ArrayList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMoviesHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ListMoviesHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ListMoviesHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.year.text = movie.year
        Glide.with(holder.banner)
            .load(movie.posterUrl)
            .apply(RequestOptions().placeholder(R.drawable.ic_movie))
            .into(holder.banner)
        holder.itemMovie.setOnClickListener { view -> movieClickedListener.movieClicked(movie.imdbId) }
    }


    class ListMoviesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.movie_title
        var year = itemView.movie_year
        var banner = itemView.movie_banner
        var itemMovie = itemView.item_movie
    }
}