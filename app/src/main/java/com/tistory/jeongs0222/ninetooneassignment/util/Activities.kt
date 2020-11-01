package com.tistory.jeongs0222.ninetooneassignment.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast


fun Activity.showPermissionAlertDialog() {
    AlertDialog.Builder(applicationContext).apply {
        setTitle("권한 설정")
        setMessage("해당 앱의 원활한 기능을 이용하시려면 애플리케이션 정보 > 권한에서 모든 권한을 허용해주세요.")
        setCancelable(false)
        setPositiveButton("설정") { _, _ ->
            startActivity(
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.parse("package:$packageName"))
            )
        }
    }.create().show()
}

fun Activity.showToastMessage(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}