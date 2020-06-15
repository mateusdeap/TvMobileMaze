package com.example.tvmobilemaze.screens.showdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tvmobilemaze.Episode
import com.example.tvmobilemaze.R
import com.example.tvmobilemaze.screens.common.BaseObservableView

class EpisodeItemView(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?,
    private val convertView: View?
) : IEpisodeItemView, BaseObservableView<IEpisodeItemView.EpisodeItemListener>() {

    override val rootView: View = convertView ?: layoutInflater.inflate(R.layout.episode_item_layout, parent, false)

    private lateinit var episode: Episode

    init {
        rootView.setOnClickListener {
            for (listener in getListeners()) {
                listener.onEpisodeClicked(episode)
            }
        }
    }

    override fun bindEpisodeItem(episode: Episode) {
        this.episode = episode
        val episodeNumberAndTitle: TextView? = findViewById(R.id.episode_number_and_title)
        episodeNumberAndTitle?.text = episode.toString()
    }
}