package com.tistory.jeongs0222.ninetooneassignment.service

import com.tistory.jeongs0222.ninetooneassignment.di.Constants.Api.KAKAO_API_KEY
import com.tistory.jeongs0222.ninetooneassignment.model.KeywordLocation
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {

    @Headers("Authorization: KakaoAK $KAKAO_API_KEY")
    @GET("v2/local/search/keyword.json")
    fun searchKeywordLocation(
        @Query("query") query: String,
        @Query("x") longitude: String,
        @Query("y") latitude: String,
        @Query("radius") radius: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): Single<KeywordLocation>

    /*@Headers("Authorization: KakaoAK $KAKAO_API_KEY")
    @GET("v2/local/search/keyword.json")
    fun searchKeywordLocation(
        @Query("query") query: String,
        @Query("size") size: Int
    ): Single<KeywordLocation>*/

}