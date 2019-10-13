package com.pavelrukin.chesstimer

import android.app.Application
import com.pavelrukin.chesstimer.di.AppComponent
import com.pavelrukin.chesstimer.di.DaggerAppComponent
import timber.log.Timber


class App : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.builder()
            .build()
    }

}