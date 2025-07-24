/*
 * Created by Saeedus Salehin on 24/7/25, 1:28 PM.
 */

package com.meldcx.appscheduler

import android.app.Application
import com.meldcx.appscheduler.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ScheduleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ScheduleApplication)
            modules(appModule)
        }
    }
}