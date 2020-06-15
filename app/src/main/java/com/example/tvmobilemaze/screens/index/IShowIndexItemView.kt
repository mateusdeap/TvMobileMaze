package com.example.tvmobilemaze.screens.index

import com.example.tvmobilemaze.domain.Show
import com.example.tvmobilemaze.screens.common.IObservableView

interface IShowIndexItemView : IObservableView<IShowIndexItemView.ShowListener> {
    interface ShowListener {
        fun onShowClicked(show: Show)
    }

    fun bindShow(show: Show)
}