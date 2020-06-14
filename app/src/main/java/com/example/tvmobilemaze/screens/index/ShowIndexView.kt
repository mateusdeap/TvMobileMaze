package com.example.tvmobilemaze.screens.index

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvmobilemaze.R
import com.example.tvmobilemaze.Show
import com.example.tvmobilemaze.screens.common.BaseObservableView
import com.example.tvmobilemaze.screens.common.BaseView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class ShowIndexView(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?,
    private val menuInflater: MenuInflater
) : BaseObservableView<IShowIndexView.ShowIndexListener>(), OnShowClickedListener, IShowIndexView,
        View.OnClickListener {

    override val rootView: View = layoutInflater.inflate(R.layout.activity_show_index, parent, false)

    private val showList: RecyclerView? = findViewById(R.id.shows_recycler_view)
    private val showListAdapter: ShowListAdapter =
        ShowListAdapter(
            layoutInflater,
            this
        )

    private val oopsTextView: TextView = findViewById(R.id.oops_text_view)
    private val nextPageFab: FloatingActionButton = findViewById(R.id.next_page_fab)
    private val previousPageFab: FloatingActionButton = findViewById(R.id.previous_page_fab)
    private val refreshFab: FloatingActionButton = findViewById(R.id.refresh_fab)

    init {
        showList?.layoutManager = LinearLayoutManager(context())
        showList?.adapter = showListAdapter
        nextPageFab.setOnClickListener(this)
        previousPageFab.setOnClickListener(this)
        refreshFab.setOnClickListener(this)
    }

    override fun onShowClicked(show: Show) {
        for (listener in getListeners()) {
            listener.onShowClicked(show)
        }
    }

    override fun showResults(result: List<Show>?) {
        if (result != null) {
            oopsTextView.visibility = View.INVISIBLE
            nextPageFab.show()
            previousPageFab.show()
            refreshFab.hide()
            showListAdapter.bindShows(result)
        }
    }

    override fun showError(error: Throwable?) {
        showListAdapter.bindShows(emptyList())
        oopsTextView.visibility = View.VISIBLE
        nextPageFab.hide()
        previousPageFab.hide()
        refreshFab.show()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.refresh_fab -> refreshShows()
            R.id.next_page_fab -> goToNextPage()
            R.id.previous_page_fab -> goToPreviousPage()
        }
    }

    private fun refreshShows() {
        for (listener in getListeners()) {
            listener.onRefreshClicked()
        }
    }

    private fun goToNextPage() {
        for (listener in getListeners()) {
            listener.goToNextPage()
        }
    }

    private fun goToPreviousPage() {
        for (listener in getListeners()) {
            listener.goToPreviousPage()
        }
    }
}