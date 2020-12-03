package com.example.marvelgallery.presenters

import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BasePresenter : Presenter {
    var subscriptions = CompositeDisposable()

    override fun onViewDestroyed() {
        subscriptions.dispose()
    }
}