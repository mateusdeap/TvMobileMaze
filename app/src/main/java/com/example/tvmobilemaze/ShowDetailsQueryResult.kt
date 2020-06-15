package com.example.tvmobilemaze

data class ShowDetailsQueryResult(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val schedule: Schedule,
    val image: Image?,
    val summary: String,
    val _embedded: Embedded
)