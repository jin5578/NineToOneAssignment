package com.tistory.jeongs0222.ninetooneassignment.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.tistory.jeongs0222.ninetooneassignment.R
import com.tistory.jeongs0222.ninetooneassignment.databinding.ActivityMainBinding
import com.tistory.jeongs0222.ninetooneassignment.ui.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }

        viewModel.searchText.observe(this, Observer {

        })
    }

}