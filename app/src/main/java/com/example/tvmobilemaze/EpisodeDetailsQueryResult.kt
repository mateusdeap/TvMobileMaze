package com.example.tvmobilemaze

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
