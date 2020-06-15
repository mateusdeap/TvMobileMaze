package com.example.tvmobilemaze

class Episode(
    val id: Int,
    val name: String,
    val number: Int,
    val season: Int,
    val summary: String,
    val image: Image?
) {
    override fun toString(): String {
        return "$number - $name"
    }
}
