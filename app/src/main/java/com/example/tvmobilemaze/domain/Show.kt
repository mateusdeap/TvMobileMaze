package com.example.tvmobilemaze.domain

data class Show(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val summary: String,
    val image: Image?
)