package com.example.tvmobilemaze.screens.index

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.widget.Toast
import com.example.tvmobilemaze.Show
import com.example.tvmobilemaze.TvMazeApi
import com.example.tvmobilemaze.constants.Constants
import com.example.tvmobilemaze.screens.common.BaseActivity
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
        disposable = tvMazeApi.showList(pageNumber)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> showIndexView.showResults(result) },
                { error -> showIndexView.showError(error) }
            )
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
    }

    override fun onShowClicked(show: Show) {
        Toast.makeText(this, show.name, Toast.LENGTH_LONG).show()
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
