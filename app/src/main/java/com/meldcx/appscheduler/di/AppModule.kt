/*
 * Created by Saeedus Salehin on 24/7/25, 1:29 PM.
 */

package com.meldcx.appscheduler.di

import androidx.room.Room
import com.meldcx.appscheduler.schedule.data.AlarmSchedulerImpl
import com.meldcx.appscheduler.schedule.data.database.AppDatabase
import com.meldcx.appscheduler.schedule.domain.AlarmScheduler
import com.meldcx.appscheduler.schedule.domain.GetApps
import com.meldcx.appscheduler.schedule.presentation.ScheduleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ScheduleViewModel(get(), get(), get()) }

    single { GetApps(get()) }

    single<AlarmScheduler> { AlarmSchedulerImpl(androidContext()) }

    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "app_database").build() }

    single { get<AppDatabase>().scheduledAlarmDao() }
}