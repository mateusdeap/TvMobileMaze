package com.example.tvmobilemaze.screens.showdetails

import com.example.tvmobilemaze.domain.Episode

interface IEpisodeClickedListener {
    fun onEpisodeClicked(episode: Episode)
}