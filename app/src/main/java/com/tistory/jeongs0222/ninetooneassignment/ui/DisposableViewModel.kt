package com.tistory.jeongs0222.ninetooneassignment.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class DisposableViewModel : ViewModel() {

    infix fun CompositeDisposable.add(d: Disposable) = this.add(d)

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()

        super.onCleared()
    }
}