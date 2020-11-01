package com.tistory.jeongs0222.ninetooneassignment.model.args

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class WebViewArgs(
    val placeUrl: String
) : Parcelable