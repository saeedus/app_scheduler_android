/*
 * Created by Saeedus Salehin on 24/7/25, 1:28 PM.
 */

package com.sazim.appscheduler

import android.app.Application
import com.sazim.appscheduler.di.appModule
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