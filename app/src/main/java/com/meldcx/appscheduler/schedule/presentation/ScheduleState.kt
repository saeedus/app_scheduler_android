/*
 * Created by Saeedus Salehin on 24/7/25, 6:49 PM.
 */

package com.meldcx.appscheduler.schedule.presentation

import com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm
import com.meldcx.appscheduler.schedule.domain.model.AppInfo

data class ScheduleState(
    val isLoading: Boolean = false,
    val apps: List<AppInfo> = emptyList(),
    val selectedApp: AppInfo? = null,
    val allScheduledAlarms: List<ScheduledAlarm> = emptyList()
)