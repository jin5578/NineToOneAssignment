package com.tistory.jeongs0222.ninetooneassignment.ui.main

import android.os.Bundle
import com.tistory.jeongs0222.ninetooneassignment.R
import com.tistory.jeongs0222.ninetooneassignment.databinding.ActivityMainBinding
import com.tistory.jeongs0222.ninetooneassignment.ui.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}