package com.example.tvmobilemaze.screens.index

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import com.example.tvmobilemaze.domain.Show
import com.example.tvmobilemaze.networking.TvMazeApi
import com.example.tvmobilemaze.constants.Constants
import com.example.tvmobilemaze.screens.common.BaseActivity
import com.example.tvmobilemaze.screens.showdetails.ShowDetailsActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ShowIndexActivity : BaseActivity(), IShowIndexView.ShowIndexListener {

    private lateinit var showIndexView: IShowIndexView

    private lateinit var tvMazeApi: TvMazeApi
    private var disposable: Disposable? = null
    private var page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showIndexView = ShowIndexView(
            LayoutInflater.from(this),
            null,
            menuInflater
        )
        setContentView(showIndexView.rootView)

        tvMazeApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvMazeApi::class.java)
    }

    override fun onStart() {
        super.onStart()
        showIndexView.registerListener(this)
        fetchPage(1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return showIndexView.inflateMenu(menu, componentName)
    }

    private fun fetchPage(pageNumber: Int) {
        showIndexView.showLoading()
        disposable = tvMazeApi.showList(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> exhibitResults(result) },
                { error -> exhibitError(error) }
            )
    }

    private fun exhibitError(error: Throwable?) {
        showIndexView.hideLoading()
        showIndexView.showError(error)
    }

    private fun exhibitResults(result: List<Show>?) {
        showIndexView.hideLoading()
        showIndexView.showResults(result)
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    override fun onShowClicked(show: Show) {
        val intent = Intent(this, ShowDetailsActivity::class.java).apply {
            putExtra("showId", show.id)
        }
        startActivity(intent)
    }

    override fun onRefreshClicked() {
        fetchPage(page)
    }

    override fun goToNextPage() {
        page += 1
        fetchPage(page)
    }

    override fun goToPreviousPage() {
        page -= 1
        fetchPage(page)
    }
}
