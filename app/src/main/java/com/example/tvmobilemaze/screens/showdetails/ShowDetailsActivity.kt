package com.example.tvmobilemaze.screens.showdetails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.example.tvmobilemaze.domain.Episode
import com.example.tvmobilemaze.networking.TvMazeApi
import com.example.tvmobilemaze.constants.Constants
import com.example.tvmobilemaze.screens.common.BaseActivity
import com.example.tvmobilemaze.screens.episodedetails.EpisodeDetailsActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ShowDetailsActivity : BaseActivity(), IShowDetailsView.ShowDetailsListener {

    private lateinit var showDetailsView: IShowDetailsView

    private lateinit var tvMazeApi: TvMazeApi
    private var disposable: Disposable? = null

    private var showId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showDetailsView = ShowDetailsView(LayoutInflater.from(this), null, menuInflater)
        setContentView(showDetailsView.rootView)

        showId = intent.extras?.get("showId") as Int

        tvMazeApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvMazeApi::class.java)
    }

    override fun onStart() {
        super.onStart()
        showDetailsView.registerListener(this)
        disposable = tvMazeApi.showDetails(showId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showDetailsView.exhibitShowInfo(result) },
                { error -> showDetailsView.showError(error) }
            )
    }

    override fun onEpisodeSelected(episode: Episode) {
        val intent = Intent(this, EpisodeDetailsActivity::class.java).apply {
            putExtra("episodeId", episode.id)
        }
        startActivity(intent)
    }
}
