package com.example.tvmobilemaze.screens.common

import android.content.Context
import android.view.View

abstract class BaseView : IView {
    protected fun <T: View> findViewById(id: Int): T {
        return rootView.findViewById(id)
    }

    protected fun context(): Context {
        return rootView.context
    }
}