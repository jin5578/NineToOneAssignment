package com.tistory.jeongs0222.ninetooneassignment.domain

import com.tistory.jeongs0222.ninetooneassignment.model.KeywordLocation
import io.reactivex.Single


interface MainRepository {

    fun searchKeywordLocation(
        query: String,
        size: Int
    ): Single<KeywordLocation>

}