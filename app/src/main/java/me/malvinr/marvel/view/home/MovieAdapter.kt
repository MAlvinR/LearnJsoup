package me.malvinr.marvel.view.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.movie_item.view.*
import me.malvinr.marvel.model.Movie
import me.malvinr.marvel.R
import me.malvinr.marvel.util.loadImage

class MovieAdapter(private val listener: MovieListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var mData = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(mData[position], listener)
    }

    interface MovieListener {
        fun onMovieClick(movie: Movie)
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(item: Movie, listener: MovieListener) = with(itemView) {
            tv_title.text = item.title
            tv_date.text = item.date
            iv_thumb.loadImage(item.image)

            setOnClickListener { listener.onMovieClick(item) }

        }
    }

    fun addData(data: List<Movie>) {
        mData.addAll(data)
        notifyDataSetChanged()
    }
}