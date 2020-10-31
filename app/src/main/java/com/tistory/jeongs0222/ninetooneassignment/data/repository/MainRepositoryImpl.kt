package com.tistory.jeongs0222.ninetooneassignment.data.repository

import com.tistory.jeongs0222.ninetooneassignment.domain.MainRepository
import com.tistory.jeongs0222.ninetooneassignment.model.KeywordLocation
import com.tistory.jeongs0222.ninetooneassignment.service.ApiService
import io.reactivex.Single


class MainRepositoryImpl(
    private val apiService: ApiService
) : MainRepository {

    override fun searchKeywordLocation(query: String, size: Int): Single<KeywordLocation> =
        apiService.searchKeywordLocation(query, size)

}