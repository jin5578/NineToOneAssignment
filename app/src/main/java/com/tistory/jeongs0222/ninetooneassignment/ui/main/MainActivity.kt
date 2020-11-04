package com.tistory.jeongs0222.ninetooneassignment.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.jakewharton.rxbinding2.widget.RxTextView
import com.tistory.jeongs0222.ninetooneassignment.R
import com.tistory.jeongs0222.ninetooneassignment.databinding.ActivityMainBinding
import com.tistory.jeongs0222.ninetooneassignment.ui.BaseActivity
import com.tistory.jeongs0222.ninetooneassignment.ui.webview.WebViewActivity
import com.tistory.jeongs0222.ninetooneassignment.util.showPermissionAlertDialog
import com.tistory.jeongs0222.ninetooneassignment.util.showToastMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main
    private val viewModel by viewModel<MainViewModel>()

    private val LOCATION_PERMISSION_CODE = 111

    private val permissionCheckList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val adapter by lazy { LocationListAdapter(this, viewModel) }
    private val locationManager by lazy { getSystemService(Context.LOCATION_SERVICE) as LocationManager }

    private lateinit var latitude: String
    private lateinit var longitude: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.apply {
            adapter = this@MainActivity.adapter
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }

        setInitView()

        checkPermission()

        viewModel.showToast.observe(this, Observer {
            showToastMessage(it)
        })

        viewModel.navigateToWebView.observe(this, Observer {
            startActivity(
                Intent(this, WebViewActivity::class.java)
                    .putExtra("webViewArgs", it)
            )
        })

        viewModel.locationFlow.observe(this, Observer {
            lifecycleScope.launch {
                it.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        })
    }

    @SuppressLint("CheckResult")
    private fun setInitView() {
        RxTextView.textChanges(viewDataBinding.search)
            .throttleLast(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe({ char ->
                if (char.toString() != "") {
                    viewModel.searchLocation(char.toString(), longitude, latitude)
                }
            }, {
                it.printStackTrace()
            })
    }

    private fun checkPermission() {
        if (
            (ContextCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            requestPermission()
        } else {
            requestLocation()
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, permissionCheckList, LOCATION_PERMISSION_CODE)
    }

    private fun requestLocation() {
        val location = if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        } else {
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
        }

        latitude = location.latitude.toString()
        longitude = location.longitude.toString()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    requestLocation()
                } else {
                    showPermissionAlertDialog()
                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onRestart() {
        super.onRestart()

        checkPermission()
    }

}