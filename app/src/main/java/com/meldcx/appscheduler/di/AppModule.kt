/*
 * Created by Saeedus Salehin on 24/7/25, 1:29 PM.
 */

package com.meldcx.appscheduler.di

import com.meldcx.appscheduler.schedule.domain.GetApps
import com.meldcx.appscheduler.schedule.presentation.ScheduleViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ScheduleViewModel(get()) }

    single { GetApps(get()) }
}