package com.tistory.jeongs0222.ninetooneassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.ninetooneassignment.domain.MainRepository
import com.tistory.jeongs0222.ninetooneassignment.model.Document
import com.tistory.jeongs0222.ninetooneassignment.model.KeywordLocation
import com.tistory.jeongs0222.ninetooneassignment.ui.DisposableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class MainViewModel(
    private val repository: MainRepository
) : DisposableViewModel(), MainEventListener {

    private val _locationList = MutableLiveData<MutableList<Document>>()
    val locationList: LiveData<MutableList<Document>>
        get() = _locationList


    fun searchLocation(query: String, longitude: String, latitude: String) {
        compositeDisposable add
                repository.searchKeywordLocation(query, longitude, latitude, 20000, 10, "distance")
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
        _locationList.value = result.documents
    }

    private fun onErrorException(it: Throwable) {
        it.printStackTrace()
    }

    override fun locationItemClicked(placeUrl: String) {

    }
    
}

interface MainEventListener {
    fun locationItemClicked(placeUrl: String)
}