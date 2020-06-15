package com.example.tvmobilemaze.screens.index

import android.content.ComponentName
import android.view.Menu
import com.example.tvmobilemaze.domain.Show
import com.example.tvmobilemaze.screens.common.IObservableView

interface IShowIndexView : IObservableView<IShowIndexView.ShowIndexListener> {
    interface ShowIndexListener {
        fun onShowClicked(show: Show)
        fun onRefreshClicked()
        fun goToNextPage()
        fun goToPreviousPage()
    }

    fun showResults(result: List<Show>?)
    fun showError(error: Throwable?)
    fun inflateMenu(menu: Menu?, componentName: ComponentName): Boolean
}