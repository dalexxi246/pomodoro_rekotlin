package com.wh2soft.tddexample

import android.app.Application
import com.wh2soft.tddexample.redux.epicsMiddleware

class App : Application() {

    override fun onTerminate() {
        super.onTerminate()
        epicsMiddleware.dispose()
    }
}