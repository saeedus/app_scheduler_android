/*
 * Created by Saeedus Salehin on 24/7/25, 1:29 PM.
 */

package com.sazim.appscheduler.di

import com.sazim.appscheduler.schedule.presentation.ScheduleViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ScheduleViewModel() }
}