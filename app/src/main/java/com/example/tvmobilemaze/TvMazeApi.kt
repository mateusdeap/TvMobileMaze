package com.example.tvmobilemaze

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {
    @GET("/shows")
    fun showList(@Query("page") pageNumber: Int): Observable<List<Show>>
}