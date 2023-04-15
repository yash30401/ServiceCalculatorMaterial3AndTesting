package com.material.service.calculator

import android.app.Application
import com.google.android.material.color.DynamicColors

class dyanmicColorApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}