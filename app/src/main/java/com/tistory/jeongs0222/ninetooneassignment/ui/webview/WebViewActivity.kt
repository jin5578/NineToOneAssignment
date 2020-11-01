package com.tistory.jeongs0222.ninetooneassignment.ui.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import com.tistory.jeongs0222.ninetooneassignment.databinding.ActivityWebViewBinding
import com.tistory.jeongs0222.ninetooneassignment.ui.BaseActivity
import com.tistory.jeongs0222.ninetooneassignment.R
import com.tistory.jeongs0222.ninetooneassignment.model.args.WebViewArgs
import org.koin.androidx.viewmodel.ext.android.viewModel


class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override val layoutResourceId: Int = R.layout.activity_web_view
    private val viewModel by viewModel<WebViewViewModel>()

    private lateinit var args: WebViewArgs


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        args = intent.getParcelableExtra("webViewArgs")!!

        viewDataBinding.apply {
            viewModel = this@WebViewActivity.viewModel
            lifecycleOwner = this@WebViewActivity
        }

        setInitWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setInitWebView() {
        viewDataBinding.webview.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(args.placeUrl)
        }
    }

    override fun onBackPressed() {
        finish()
    }

}