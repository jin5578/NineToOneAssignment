package com.tistory.jeongs0222.ninetooneassignment

import android.app.Application
import com.tistory.jeongs0222.ninetooneassignment.di.AppModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber


class NineToOneApp : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin(this, AppModule)
    }
}