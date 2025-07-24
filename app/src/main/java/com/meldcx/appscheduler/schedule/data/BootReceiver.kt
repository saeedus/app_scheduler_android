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
                    alarmScheduler.schedule(alarm.packageName, alarm.timeInMillis)
                }
            }
        }
    }
}
