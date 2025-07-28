/*
 * Created by Saeedus Salehin on 24/7/25, 8:25 PM.
 */

package com.meldcx.appscheduler.schedule.domain

import com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm

interface AlarmScheduler {
    fun schedule(alarm: ScheduledAlarm)
    fun cancel(alarm: ScheduledAlarm)
}