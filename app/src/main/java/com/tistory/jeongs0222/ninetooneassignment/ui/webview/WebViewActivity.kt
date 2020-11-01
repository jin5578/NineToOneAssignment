package com.tistory.jeongs0222.ninetooneassignment.ui.webview

import android.os.Bundle
import com.tistory.jeongs0222.ninetooneassignment.databinding.ActivityWebViewBinding
import com.tistory.jeongs0222.ninetooneassignment.ui.BaseActivity
import com.tistory.jeongs0222.ninetooneassignment.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override val layoutResourceId: Int = R.layout.activity_web_view
    private val viewModel by viewModel<WebViewViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.apply {
            viewModel = this@WebViewActivity.viewModel
            lifecycleOwner = this@WebViewActivity
        }
    }

}