package com.tistory.jeongs0222.ninetooneassignment.ui.webview

import org.koin.dsl.module.module
import org.koin.androidx.viewmodel.ext.koin.viewModel


val WebViewModule = module {

    viewModel {
        WebViewViewModel()
    }

}