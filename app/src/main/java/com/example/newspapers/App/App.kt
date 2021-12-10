package com.example.newspapers.App

import android.app.Application
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

class App : Application() {
    lateinit var appManager:AppManager
    override fun onCreate() {
        super.onCreate()
        appManager = AppManager(this)
        registerActivityLifecycleCallbacks(appManager)
        ProcessLifecycleOwner.get().lifecycle.addObserver(object : DefaultLifecycleObserver {

            override fun onStart(owner: LifecycleOwner) {
                appManager.isShowingAd = false
            }

            override fun onStop(owner: LifecycleOwner) {
                appManager.isShowingAd = true
            }
        })
    }
}