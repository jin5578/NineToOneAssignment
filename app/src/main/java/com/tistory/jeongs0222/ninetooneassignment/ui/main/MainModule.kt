package com.tistory.jeongs0222.ninetooneassignment.ui.main

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


val MainModule = module {

    viewModel {
        MainViewModel()
    }

}