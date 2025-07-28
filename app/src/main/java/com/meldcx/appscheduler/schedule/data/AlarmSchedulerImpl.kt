/*
 * Created by Saeedus Salehin on 24/7/25, 8:26 PM.
 */

package com.meldcx.appscheduler.schedule.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.meldcx.appscheduler.schedule.domain.AlarmScheduler

import com.meldcx.appscheduler.util.Constants

class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    override fun schedule(packageName: String, timeInMillis: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(Constants.PACKAGE_NAME_KEY, packageName)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            packageName.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            timeInMillis,
            pendingIntent
        )
    }
}