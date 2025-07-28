package com.meldcx.appscheduler.schedule.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.meldcx.appscheduler.schedule.data.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val db = AppDatabase.getInstance(context)
            val scheduledAlarmDao = db.scheduledAlarmDao()
            val alarmScheduler = AlarmSchedulerImpl(context)

            CoroutineScope(Dispatchers.IO).launch {
                val scheduledAlarms = scheduledAlarmDao.getAll()
                scheduledAlarms.forEach { alarm ->
                    if (!alarm.isExecuted) {
                        val calendar = java.util.Calendar.getInstance().apply {
                            set(java.util.Calendar.YEAR, alarm.year)
                            set(java.util.Calendar.MONTH, alarm.month)
                            set(java.util.Calendar.DAY_OF_MONTH, alarm.day)
                            set(java.util.Calendar.HOUR_OF_DAY, alarm.hour)
                            set(java.util.Calendar.MINUTE, alarm.minute)
                            set(java.util.Calendar.SECOND, 0)
                            set(java.util.Calendar.MILLISECOND, 0)
                        }
                        if (calendar.timeInMillis > System.currentTimeMillis()) {
                            alarmScheduler.schedule(alarm)
                        }
                    }
                }
            }
        }
    }
}