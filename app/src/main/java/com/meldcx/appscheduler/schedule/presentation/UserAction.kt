/*
 * Created by Saeedus Salehin on 24/7/25, 7:23 PM.
 */

package com.meldcx.appscheduler.schedule.presentation

import com.meldcx.appscheduler.schedule.domain.model.AppInfo

sealed class UserAction {
    data object LoadApps : UserAction()
    data class AppSelected(val appInfo: AppInfo) : UserAction()
    data class Schedule(val timeInMillis: Long) : UserAction()
}