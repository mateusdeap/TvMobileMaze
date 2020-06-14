package com.example.tvmobilemaze.screens.common

interface IObservableView<Listener> : IView {
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
}