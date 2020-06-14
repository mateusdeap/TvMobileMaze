package com.example.tvmobilemaze.screens.common

import java.util.*
import kotlin.collections.HashSet

abstract class BaseObservableView<Listener> : BaseView(), IObservableView<Listener> {
    private val listeners: MutableSet<Listener> = HashSet();

    override fun registerListener(listener: Listener) {
        listeners.add(listener)
    }

    override fun unregisterListener(listener: Listener) {
        listeners.remove(listener)
    }

    protected fun getListeners(): Set<Listener> {
        return Collections.unmodifiableSet(listeners)
    }
}