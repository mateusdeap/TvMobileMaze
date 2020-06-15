package com.example.tvmobilemaze.screens.episodedetails

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tvmobilemaze.Episode
import com.example.tvmobilemaze.Image
import com.example.tvmobilemaze.R
import com.example.tvmobilemaze.screens.common.BaseObservableView
import com.squareup.picasso.Picasso

class EpisodeDetailsView(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?,
    private val menuInflater: MenuInflater
) : IEpisodeDetailsView, BaseObservableView<IEpisodeDetailsView.EpisodeDetailsListener>() {

    override val rootView: View = layoutInflater.inflate(R.layout.activity_episode_details, parent, false)

    private val thumbnailImageView: ImageView = findViewById(R.id.episode_thumbnail)
    private val numberAndTitleTextView: TextView = findViewById(R.id.episode_details_number_and_title)
    private val seasonTextView: TextView = findViewById(R.id.episode_details_season)
    private val summaryTextView: TextView = findViewById(R.id.episode_details_summary)

    override fun bindEpisode(episode: Episode) {
        bindEpisodeThumbnail(episode.image)
        numberAndTitleTextView.text = episode.toString()
        seasonTextView.text = episode.seasonToString()
        summaryTextView.text = episode.summary
    }

    override fun showError(error: Throwable?) {
        TODO("Not yet implemented")
    }

    private fun bindEpisodeThumbnail(image: Image?) {
        Picasso.get()
            .load(image?.original)
            .placeholder(R.drawable.placeholder_128dp)
            .error(R.drawable.placeholder_128dp)
            .into(thumbnailImageView)
    }
}