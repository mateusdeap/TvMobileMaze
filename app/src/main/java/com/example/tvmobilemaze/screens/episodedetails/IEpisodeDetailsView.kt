package com.example.tvmobilemaze.screens.episodedetails

import com.example.tvmobilemaze.Episode
import com.example.tvmobilemaze.screens.common.IObservableView

interface IEpisodeDetailsView : IObservableView<IEpisodeDetailsView.EpisodeDetailsListener> {
    interface EpisodeDetailsListener {
        fun onBackClicked()
    }

    fun bindEpisode(episode: Episode)
    fun showError(error: Throwable?)
}