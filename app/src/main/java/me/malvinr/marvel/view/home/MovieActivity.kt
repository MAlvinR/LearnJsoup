package me.malvinr.marvel.view.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import me.malvinr.marvel.model.Movie
import me.malvinr.marvel.R
import me.malvinr.marvel.util.toGone
import me.malvinr.marvel.util.toVisible

class MovieActivity : AppCompatActivity(), MovieView, MovieAdapter.MovieListener {

    private lateinit var presenter: MoviePresenter
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        disableTitleFromToolbar()
        setupRecyclerView()

        presenter = MoviePresenter(this)
        presenter.getMarvelCinematicUniverseLists()
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter(this@MovieActivity)

        rv_movie.apply {
            layoutManager = GridLayoutManager(this@MovieActivity, 2)
            adapter = movieAdapter
        }
    }

    override fun onProgress() {
        pb_movie.toVisible()
    }

    override fun onSuccess(movie: List<Movie>) {
        pb_movie.toGone()
        movieAdapter.addData(movie)
    }

    override fun onError(message: String) {
        pb_movie.toGone()
        Log.d("ERROR : ", message)
    }

    override fun onMovieClick(movie: Movie) {
        Toast.makeText(this@MovieActivity, movie.title, Toast.LENGTH_SHORT).show()
    }

    private fun disableTitleFromToolbar() {
        if (supportActionBar != null)
            supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}