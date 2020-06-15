package com.example.tvmobilemaze.screens.showlookup

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.view.*
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvmobilemaze.R
import com.example.tvmobilemaze.domain.Show
import com.example.tvmobilemaze.networking.ShowQueryItem
import com.example.tvmobilemaze.screens.common.BaseObservableView
import com.example.tvmobilemaze.screens.index.OnShowClickedListener
import com.example.tvmobilemaze.screens.index.ShowListAdapter

class ShowLookupView(
    private val layoutInflater: LayoutInflater,
    private val parent: ViewGroup?,
    private val menuInflater: MenuInflater
) : BaseObservableView<IShowLookupView.ShowLookupListener>(), OnShowClickedListener, IShowLookupView {
    override val rootView: View = layoutInflater.inflate(R.layout.activity_show_lookup, parent, false)

    private val showList: RecyclerView? = findViewById(R.id.show_lookup_recycler_view)
    private val showListAdapter: ShowListAdapter =
        ShowListAdapter(
            layoutInflater,
            this
        )

    init {
        showList?.layoutManager = LinearLayoutManager(context())
        showList?.adapter = showListAdapter
    }

    override fun onShowClicked(show: Show) {
        for (listener in getListeners()) {
            listener.onShowClicked(show)
        }
    }

    override fun showQueryResults(showQueryReturn: List<ShowQueryItem>?) {
        if ( showQueryReturn != null) {
            val shows = ArrayList<Show>()
            for (showQueryItem in showQueryReturn) {
                shows.add(showQueryItem.show)
            }
            showListAdapter.bindShows(shows)
        }
    }

    override fun showError(error: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun inflateMenu(menu: Menu?, componentName: ComponentName): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchManager = context().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            isIconifiedByDefault = false
        }

        return true
    }
}