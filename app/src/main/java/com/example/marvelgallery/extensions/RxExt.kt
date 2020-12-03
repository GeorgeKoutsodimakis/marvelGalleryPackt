package com.example.marvelgallery.extensions

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(): Single<T> =
    this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribeBy(
    onError: ((Throwable) -> Unit)? = null,
    onSuccess: (T) -> Unit
): Disposable = subscribe(onSuccess, { onError?.invoke(it) })

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}