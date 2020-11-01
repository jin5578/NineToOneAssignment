package com.tistory.jeongs0222.ninetooneassignment.domain

import com.tistory.jeongs0222.ninetooneassignment.model.kakao.KeywordLocation
import io.reactivex.Single


interface MainRepository {

    fun searchKeywordLocation(
        query: String,
        longitude: String,
        latitude: String,
        radius: Int,
        size: Int,
        sort: String
    ): Single<KeywordLocation>

}