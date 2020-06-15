package com.example.tvmobilemaze.networking

import com.example.tvmobilemaze.domain.Episode
import com.example.tvmobilemaze.domain.Image
import com.example.tvmobilemaze.domain.Show

class EpisodeDetailsQueryResult(
    val id: Int,
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String,
    val image: Image?,
    val _embedded: Show
) {

    fun episode(): Episode {
        return Episode(
            id, name, number, season, summary, image
        )
    }
}
