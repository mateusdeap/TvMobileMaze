package com.example.tvmobilemaze.networking

import com.example.tvmobilemaze.domain.Image
import com.example.tvmobilemaze.domain.Schedule
import com.example.tvmobilemaze.networking.Embedded

data class ShowDetailsQueryResult(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val schedule: Schedule,
    val image: Image?,
    val summary: String,
    val _embedded: Embedded
)