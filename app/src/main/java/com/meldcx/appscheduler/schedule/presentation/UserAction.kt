/*
 * Created by Saeedus Salehin on 24/7/25, 7:23 PM.
 */

package com.meldcx.appscheduler.schedule.presentation

import com.meldcx.appscheduler.schedule.domain.model.AppInfo

sealed class UserAction {
    data object LoadApps : UserAction()
    data class AppSelected(val appInfo: AppInfo) : UserAction()
    data class Schedule(val year: Int, val month: Int, val day: Int, val hour: Int, val minute: Int) : UserAction()
    data class UpdateSchedule(val scheduledAlarm: com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm) : UserAction()
    data class CancelSchedule(val scheduledAlarm: com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm) : UserAction()
}