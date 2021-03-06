package com.example.tvmobilemaze.networking

import com.example.tvmobilemaze.domain.Show
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApi {
    @GET("/shows")
    fun showList(@Query("page") pageNumber: Int): Observable<List<Show>>

    @GET("search/shows")
    fun getShow(@Query("q") showName: String): Observable<List<ShowQueryItem>>

    @GET("/shows/{id}?embed[]=episodes&embed[]=seasons")
    fun showDetails(@Path("id") showId: Int): Observable<ShowDetailsQueryResult>

    @GET("/episodes/{id}?embed=show")
    fun episodeDetails(@Path("id") episodeId: Int): Observable<EpisodeDetailsQueryResult>
}