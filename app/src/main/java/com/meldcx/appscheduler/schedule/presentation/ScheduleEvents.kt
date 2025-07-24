/*
 * Created by Saeedus Salehin on 24/7/25, 6:55 PM.
 */

package com.meldcx.appscheduler.schedule.presentation

sealed class ScheduleEvents {
    data object NavigateToScheduler : ScheduleEvents()

}