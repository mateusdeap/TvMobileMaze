package com.example.tvmobilemaze.screens.episodedetails

import android.os.Bundle
import android.view.LayoutInflater
import com.example.tvmobilemaze.networking.EpisodeDetailsQueryResult
import com.example.tvmobilemaze.networking.TvMazeApi
import com.example.tvmobilemaze.constants.Constants
import com.example.tvmobilemaze.screens.common.BaseActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class EpisodeDetailsActivity : BaseActivity(), IEpisodeDetailsView.EpisodeDetailsListener {

    private lateinit var episodeDetailsView: IEpisodeDetailsView

    private lateinit var tvMazeApi: TvMazeApi
    private var disposable: Disposable? = null

    private var episodeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        episodeDetailsView = EpisodeDetailsView(LayoutInflater.from(this), null, menuInflater)
        setContentView(episodeDetailsView.rootView)

        episodeId = intent.extras?.get("episodeId") as Int

        tvMazeApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvMazeApi::class.java)
    }

    override fun onStart() {
        super.onStart()
        episodeDetailsView.registerListener(this)
        fetchEpisode(episodeId)
    }

    private fun fetchEpisode(episodeId: Int) {
        disposable = tvMazeApi.episodeDetails(episodeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> exhibitEpisodeDetails(result) },
                { error -> episodeDetailsView.showError(error) }
            )
    }

    private fun exhibitEpisodeDetails(result: EpisodeDetailsQueryResult) {
        episodeDetailsView.bindEpisode(result.episode())
    }

    override fun onBackClicked() {
        TODO("Not yet implemented")
    }
}
