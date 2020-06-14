package com.example.tvmobilemaze.screens.index

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tvmobilemaze.R
import com.example.tvmobilemaze.Show
import com.example.tvmobilemaze.screens.common.BaseObservableView
import com.example.tvmobilemaze.screens.common.BaseView
import com.squareup.picasso.Picasso

class ShowIndexItemView(private val layoutInflater: LayoutInflater, private val parent: ViewGroup)
    : BaseObservableView<IShowIndexItemView.ShowListener>(), IShowIndexItemView {
    override val rootView: View = layoutInflater.inflate(R.layout.show_list_item, parent, false)

    private val titleTextView: TextView = findViewById(R.id.show_title)
    private val genresTextView: TextView = findViewById(R.id.genres)
    private val posterImageView: ImageView = findViewById(R.id.show_poster)

    private lateinit var show: Show

    init {
        rootView.setOnClickListener {
            for (listener in getListeners()) {
                listener.onShowClicked(show)
            }
        }
    }

    override fun bindShow(show: Show) {
        this.show = show
        titleTextView.text = show.name
        genresTextView.text = show.genres.toString()
        Picasso.get()
            .load(show.image?.medium)
            .placeholder(R.drawable.placeholder_128dp)
            .error(R.drawable.placeholder_128dp)
            .into(posterImageView)
    }
}
