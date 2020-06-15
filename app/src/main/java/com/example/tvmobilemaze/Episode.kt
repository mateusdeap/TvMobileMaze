package com.example.tvmobilemaze

class Episode(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int
) {
    override fun toString(): String {
        return "$number - $name"
    }
}
