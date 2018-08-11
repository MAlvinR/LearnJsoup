package me.malvinr.marvel.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

internal fun ImageView.loadImage(url: String) {
    setMemoryCategory(this.context)

    GlideApp.with(context).clear(this)
    GlideApp.with(context)
            .load(url)
            .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerCrop()
            .into(this)
}

private fun setMemoryCategory(context: Context) {
    Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
}

internal fun View.toVisible() {
    this.visibility = View.VISIBLE
}

internal fun View.toGone() {
    this.visibility = View.GONE
}