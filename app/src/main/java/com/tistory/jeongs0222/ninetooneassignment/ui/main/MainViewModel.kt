package com.tistory.jeongs0222.ninetooneassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.jeongs0222.ninetooneassignment.model.args.WebViewArgs
import com.tistory.jeongs0222.ninetooneassignment.model.kakao.Document
import com.tistory.jeongs0222.ninetooneassignment.model.kakao.KeywordLocation
import com.tistory.jeongs0222.ninetooneassignment.ui.DisposableViewModel
import com.tistory.jeongs0222.ninetooneassignment.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class MainViewModel() : DisposableViewModel(), MainEventListener {

    private val _showToast = SingleLiveEvent<String>()
    val showToast: LiveData<String>
        get() = _showToast

    private val _navigateToWebView = SingleLiveEvent<WebViewArgs>()
    val navigateToWebView: LiveData<WebViewArgs>
        get() = _navigateToWebView

    private val _locationList = MutableLiveData<MutableList<Document>>()
    val locationList: LiveData<MutableList<Document>>
        get() = _locationList


    fun searchLocation(query: String, longitude: String, latitude: String) {
        /*compositeDisposable add
                repository.searchKeywordLocation(query, longitude, latitude, 20000, 10, "distance")
                    .subscribeOn(Schedulers.io())
                    .retry(1)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        onSuccessSearchLocation(result)
                    }, {
                        onErrorException(it)
                    })*/
    }

    private fun onSuccessSearchLocation(result: KeywordLocation) {
        _locationList.value = result.documents
    }

    private fun onErrorException(it: Throwable) {
        it.printStackTrace()
    }

    override fun locationItemClicked(placeUrl: String) {
        if (validateUrl(placeUrl)) {
            _navigateToWebView.value = WebViewArgs(placeUrl)
        } else {
            _showToast.postValue("존재하지 않는 URL입니다.")
        }
    }

    private fun validateUrl(placeUrl: String): Boolean = placeUrl != ""

}

interface MainEventListener {
    fun locationItemClicked(placeUrl: String)
}