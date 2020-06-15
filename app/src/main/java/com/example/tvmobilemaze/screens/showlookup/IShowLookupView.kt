package com.example.tvmobilemaze.screens.showlookup

import android.content.ComponentName
import android.view.Menu
import com.example.tvmobilemaze.domain.Show
import com.example.tvmobilemaze.networking.ShowQueryItem
import com.example.tvmobilemaze.screens.common.IObservableView

interface IShowLookupView : IObservableView<IShowLookupView.ShowLookupListener> {

    interface ShowLookupListener {
        fun onShowClicked(show: Show)
        fun onBackClicked()
    }

    fun showQueryResults(showQueryReturn: List<ShowQueryItem>?)
    fun showError(error: Throwable?)
    fun inflateMenu(menu: Menu?, componentName: ComponentName): Boolean
}