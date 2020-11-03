package com.tistory.jeongs0222.ninetooneassignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tistory.jeongs0222.ninetooneassignment.data.pagingsource.LocationPagingSource
import com.tistory.jeongs0222.ninetooneassignment.model.args.WebViewArgs
import com.tistory.jeongs0222.ninetooneassignment.model.kakao.Document
import com.tistory.jeongs0222.ninetooneassignment.service.ApiService
import com.tistory.jeongs0222.ninetooneassignment.ui.DisposableViewModel
import com.tistory.jeongs0222.ninetooneassignment.util.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import timber.log.Timber


class MainViewModel(
    private val apiService: ApiService
) : DisposableViewModel(), MainEventListener {

    private val _showToast = SingleLiveEvent<String>()
    val showToast: LiveData<String>
        get() = _showToast

    private val _navigateToWebView = SingleLiveEvent<WebViewArgs>()
    val navigateToWebView: LiveData<WebViewArgs>
        get() = _navigateToWebView

    private val _locationFlow = MutableLiveData<Flow<PagingData<Document>>>()
    val locationFlow: LiveData<Flow<PagingData<Document>>>
        get() = _locationFlow


    fun searchLocation(query: String, longitude: String, latitude: String) {
        Timber.e("longitude%s", longitude)
        Timber.e("latitude%s", latitude)

        _locationFlow.value = Pager(PagingConfig(pageSize = 10, prefetchDistance = 10)) {
            LocationPagingSource(apiService, query, longitude, latitude)
        }.flow.cachedIn(viewModelScope)
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