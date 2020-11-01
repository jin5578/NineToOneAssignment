package com.tistory.jeongs0222.ninetooneassignment.ui.main

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tistory.jeongs0222.ninetooneassignment.model.Document


@BindingAdapter("locationList")
fun locationList(recyclerView: RecyclerView, list: MutableList<Document>?) {
    list?.let {
        (recyclerView.adapter as LocationListAdapter).apply {
            submitList(it)
        }
    }
}

@BindingAdapter("distanceFormat")
fun distanceFormat(textView: TextView, distance: String) {
    textView.text = (distance.toDouble() / 1000.0).toString()
}