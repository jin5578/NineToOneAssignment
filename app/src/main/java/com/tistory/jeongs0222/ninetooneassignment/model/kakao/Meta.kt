package com.tistory.jeongs0222.ninetooneassignment.model.kakao

import com.google.gson.annotations.SerializedName


data class Meta(
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean
)