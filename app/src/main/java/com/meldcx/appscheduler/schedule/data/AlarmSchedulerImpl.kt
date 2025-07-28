/*
 * Created by Saeedus Salehin on 24/7/25, 8:26 PM.
 */

package com.meldcx.appscheduler.schedule.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.meldcx.appscheduler.schedule.domain.AlarmScheduler

import com.meldcx.appscheduler.core.presentation.util.Constants
import com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm

class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    override fun schedule(alarm: ScheduledAlarm) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(Constants.SCHEDULED_ALARM_KEY, alarm)
        }

        val calendar = java.util.Calendar.getInstance().apply {
            set(java.util.Calendar.YEAR, alarm.year)
            set(java.util.Calendar.MONTH, alarm.month)
            set(java.util.Calendar.DAY_OF_MONTH, alarm.day)
            set(java.util.Calendar.HOUR_OF_DAY, alarm.hour)
            set(java.util.Calendar.MINUTE, alarm.minute)
            set(java.util.Calendar.SECOND, 0)
            set(java.util.Calendar.MILLISECOND, 0)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    override fun cancel(alarm: ScheduledAlarm) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(Constants.SCHEDULED_ALARM_KEY, alarm)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarm.id,
            intent,
            PendingIntent.FLAG_NO_CREATE or PendingIntent.FLAG_IMMUTABLE
        )
        pendingIntent?.cancel()
    }
}