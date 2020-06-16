package com.example.tvmobilemaze.screens.index

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tvmobilemaze.domain.Show
import java.util.*

class ShowListAdapter(private val layoutInflater: LayoutInflater, private val onShowClickedListener: OnShowClickedListener)
    : RecyclerView.Adapter<ShowIndexItemViewHolder>(), IShowIndexItemView.ShowListener {

    private var shows: List<Show> = ArrayList<Show>()

    fun bindShows(shows: List<Show>) {
        this.shows = ArrayList(shows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowIndexItemViewHolder {
        val showView = ShowIndexItemView(
            layoutInflater,
            parent
        )
        showView.registerListener(this)
        return ShowIndexItemViewHolder(
            showView
        )
    }

    override fun getItemCount(): Int {
        return shows.size
    }

    override fun onBindViewHolder(holder: ShowIndexItemViewHolder, position: Int) {
        holder.showIndexItemView.bindShow(shows[position])
    }

    override fun onShowClicked(show: Show) {
        onShowClickedListener.onShowClicked(show)
    }
}