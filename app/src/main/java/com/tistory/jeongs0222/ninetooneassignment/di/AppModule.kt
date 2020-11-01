package com.tistory.jeongs0222.ninetooneassignment.di

import com.tistory.jeongs0222.ninetooneassignment.ui.main.MainModule
import com.tistory.jeongs0222.ninetooneassignment.ui.webview.WebViewModule


val AppModule = arrayListOf(
    ApiModule,
    MainModule,
    WebViewModule
)