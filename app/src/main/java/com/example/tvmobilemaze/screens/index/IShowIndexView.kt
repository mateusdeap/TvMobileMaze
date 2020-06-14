package com.example.tvmobilemaze.screens.index

import com.example.tvmobilemaze.Show
import com.example.tvmobilemaze.screens.common.IObservableView
import com.example.tvmobilemaze.screens.common.IView

interface IShowIndexView : IObservableView<IShowIndexView.ShowIndexListener> {
    interface ShowIndexListener {
        fun onShowClicked(show: Show)
        fun onRefreshClicked()
        fun goToNextPage()
        fun goToPreviousPage()
    }

    fun showResults(result: List<Show>?)
    fun showError(error: Throwable?)
}