package com.tistory.jeongs0222.ninetooneassignment.ui.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.tistory.jeongs0222.ninetooneassignment.R
import com.tistory.jeongs0222.ninetooneassignment.databinding.ActivityMainBinding
import com.tistory.jeongs0222.ninetooneassignment.ui.BaseActivity
import com.tistory.jeongs0222.ninetooneassignment.util.showPermissionAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main
    private val viewModel by viewModel<MainViewModel>()

    private val LOCATION_PERMISSION_CODE = 111

    private val permissionCheckList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val locationManager by lazy { getSystemService(Context.LOCATION_SERVICE) as LocationManager }

    private lateinit var latitude: String
    private lateinit var longitude: String

    private val timer = Timer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding.apply {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }

        setInitView()

        checkPermission()
    }

    private fun setInitView() {
        viewDataBinding.recyclerView.adapter = LocationListAdapter(this)

        viewDataBinding.search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                if (editable != null && editable.toString() != "") {
                    timer.schedule(object : TimerTask() {
                        override fun run() {
                            runOnUiThread {
                                viewModel.searchLocation(editable.toString(), longitude, latitude)
                            }
                        }
                    }, 500)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

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

    override fun onDestroy() {
        super.onDestroy()

        timer.cancel()
    }

}