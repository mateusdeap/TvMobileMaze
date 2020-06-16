package com.example.tvmobilemaze.screens.showdetails

import com.example.tvmobilemaze.domain.Episode
import com.example.tvmobilemaze.screens.common.IObservableView

interface IEpisodeItemView : IObservableView<IEpisodeItemView.EpisodeItemListener> {
    interface EpisodeItemListener {
        fun onEpisodeClicked(episode: Episode)
    }

    fun bindEpisodeItem(episode: Episode)
}