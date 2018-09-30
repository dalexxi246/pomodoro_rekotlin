package com.wh2soft.tddexample

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import com.wh2soft.tddexample.redux.epicsMiddleware

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (!startLeakCanary()) {
            return
        }
    }

    private fun startLeakCanary() : Boolean {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return false
        }
        LeakCanary.install(this)
        return true
    }

    override fun onTerminate() {
        super.onTerminate()
        epicsMiddleware.dispose()
    }
}