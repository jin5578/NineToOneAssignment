package com.tistory.jeongs0222.ninetooneassignment.ui.main

import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.ninetooneassignment.domain.MainRepository
import com.tistory.jeongs0222.ninetooneassignment.model.KeywordLocation
import com.tistory.jeongs0222.ninetooneassignment.ui.DisposableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class MainViewModel(
    private val repository: MainRepository
) : DisposableViewModel() {

    val searchText = MutableLiveData<String>()

    init {
        searchLocation()
    }

    private fun searchLocation() {
        compositeDisposable add
                repository.searchKeywordLocation("하남", 10)
                    .subscribeOn(Schedulers.io())
                    .retry(1)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        onSuccessSearchLocation(result)
                    }, {
                        onErrorException(it)
                    })
    }

    private fun onSuccessSearchLocation(result: KeywordLocation) {
        Timber.e(result.toString())
    }

    private fun onErrorException(it: Throwable) {
        it.printStackTrace()
    }
}