package com.example.tvmobilemaze

data class Show(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val summary: String,
    val image: Image?
)