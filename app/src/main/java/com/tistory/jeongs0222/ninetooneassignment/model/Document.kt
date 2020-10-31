package com.tistory.jeongs0222.ninetooneassignment.model

import com.google.gson.annotations.SerializedName


data class Document(
    @SerializedName("place_name")
    val placeName: String,
    @SerializedName("address_name")
    val addressName: String,
    val distance: String
)