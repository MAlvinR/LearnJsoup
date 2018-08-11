package me.malvinr.marvel.view.home

import me.malvinr.marvel.model.Movie

interface MovieView {
    fun onProgress()

    fun onSuccess(movie: List<Movie>)

    fun onError(message: String)
}