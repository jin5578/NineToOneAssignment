package com.tistory.jeongs0222.ninetooneassignment.service

import com.tistory.jeongs0222.ninetooneassignment.di.Constants.Api.KAKAO_API_KEY
import com.tistory.jeongs0222.ninetooneassignment.model.kakao.KeywordLocation
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiService {

    @Headers("Authorization: KakaoAK $KAKAO_API_KEY")
    @GET("v2/local/search/keyword.json")
    fun searchKeywordLocationAsync(
        @Query("query") query: String,
        @Query("x") longitude: String,
        @Query("y") latitude: String,
        @Query("radius") radius: Int,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String
    ): Deferred<Response<KeywordLocation>>

}
