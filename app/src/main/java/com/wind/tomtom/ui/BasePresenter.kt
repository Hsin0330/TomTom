package com.wind.tomtom.ui

import io.reactivex.disposables.CompositeDisposable

open class BasePresenter {
    protected val compositeDisposable = CompositeDisposable()

    fun disposed() {
        compositeDisposable.clear()
    }
}