package com.example.tvmobilemaze.networking

import com.example.tvmobilemaze.domain.Episode
import com.example.tvmobilemaze.domain.Season

data class Embedded(
    val episodes: List<Episode>,
    val seasons: List<Season>
)
