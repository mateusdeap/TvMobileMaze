package com.example.tvmobilemaze.screens.showlookup

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import com.example.tvmobilemaze.domain.Show
import com.example.tvmobilemaze.networking.TvMazeApi
import com.example.tvmobilemaze.constants.Constants
import com.example.tvmobilemaze.networking.ShowQueryItem
import com.example.tvmobilemaze.screens.common.BaseActivity
import com.example.tvmobilemaze.screens.showdetails.ShowDetailsActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ShowLookupActivity : BaseActivity(), IShowLookupView.ShowLookupListener {

    private lateinit var showLookupView: IShowLookupView

    private lateinit var tvMazeApi: TvMazeApi
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLookupView = ShowLookupView(
            LayoutInflater.from(this),
            null,
            menuInflater
        )
        setContentView(showLookupView.rootView)

        tvMazeApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TvMazeApi::class.java)

        handleSearchIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleSearchIntent(intent)
    }

    override fun onStart() {
        super.onStart()
        showLookupView.registerListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return showLookupView.inflateMenu(menu, componentName)
    }

    private fun handleSearchIntent(intent: Intent?) {
        if (Intent.ACTION_SEARCH == intent?.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                searchForShow(query)
            }
        }
    }

    private fun searchForShow(query: String) {
        showLookupView.showLoading()
        disposable = tvMazeApi.getShow(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> exhibitResults(result) },
                { error -> exhibitError(error) }
            )
    }

    private fun exhibitError(error: Throwable?) {
        showLookupView.hideLoading()
        showLookupView.showError(error)
    }

    private fun exhibitResults(result: List<ShowQueryItem>) {
        showLookupView.hideLoading()
        showLookupView.showQueryResults(result)
    }

    override fun onShowClicked(show: Show) {
        val intent = Intent(this, ShowDetailsActivity::class.java).apply {
            putExtra("showId", show.id)
        }
        startActivity(intent)
    }

    override fun onBackClicked() {
        TODO("Not yet implemented")
    }
}
