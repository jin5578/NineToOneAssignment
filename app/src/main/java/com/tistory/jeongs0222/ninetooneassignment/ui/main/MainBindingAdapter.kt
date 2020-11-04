package com.tistory.jeongs0222.ninetooneassignment.ui.main

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter


@SuppressLint("SetTextI18n")
@BindingAdapter("distanceFormat")
fun distanceFormat(textView: TextView, distance: String) {
    val meter = distance.toDouble()
    when (meter > 1000.0) {
        true ->
            textView.text = String.format("%.1f", meter / 1000.0) + "km"

        false ->
            textView.text = distance + "m"
    }
}
