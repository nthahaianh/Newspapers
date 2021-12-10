package com.example.newspapers.App

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.example.newspapers.View.PasswordActivity

class AppManager(application: Application) : Application.ActivityLifecycleCallbacks,
    LifecycleObserver {
    var isShowingAd = true
    private var currentActivity: Activity? = null
    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
        currentActivity = activity
        Log.e("AppManager", "onActivityStarted - ${currentActivity.toString()}")
        if (isShowingAd) {
            currentActivity!!.startActivity(
                Intent(currentActivity!!.baseContext, PasswordActivity::class.java)
            )
        }
    }

    override fun onActivityResumed(activity: Activity) {
        currentActivity = activity
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}