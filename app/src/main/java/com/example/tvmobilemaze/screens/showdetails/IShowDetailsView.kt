package com.example.tvmobilemaze.screens.showdetails

import com.example.tvmobilemaze.domain.Episode
import com.example.tvmobilemaze.networking.ShowDetailsQueryResult
import com.example.tvmobilemaze.screens.common.IObservableView

interface IShowDetailsView : IObservableView<IShowDetailsView.ShowDetailsListener> {
    interface ShowDetailsListener {
        fun onEpisodeSelected(episode: Episode)
    }

    fun exhibitShowInfo(showInfo: ShowDetailsQueryResult)
    fun showError(error: Throwable?)
}