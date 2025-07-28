package com.meldcx.appscheduler.schedule.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.meldcx.appscheduler.service.AlarmService
import com.meldcx.appscheduler.core.presentation.util.Constants
import com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm
import com.meldcx.appscheduler.schedule.data.dao.ScheduledAlarmDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.getKoin

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let { it ->
            val scheduledAlarmFromIntent = it.getParcelableExtra<ScheduledAlarm>(Constants.SCHEDULED_ALARM_KEY)
            scheduledAlarmFromIntent?.let { alarmFromIntent ->
                val serviceIntent = Intent(context, AlarmService::class.java).apply {
                    putExtra(Constants.PACKAGE_NAME_KEY, alarmFromIntent.packageName)
                }
                context.startService(serviceIntent)

                CoroutineScope(Dispatchers.IO).launch {
                    val scheduledAlarmDao: ScheduledAlarmDao = getKoin().get()

                    val alarmToUpdate = scheduledAlarmDao.getAll().find { it.id == alarmFromIntent.id }
                    alarmToUpdate?.let { existingAlarm ->
                        scheduledAlarmDao.update(existingAlarm.copy(isExecuted = true))
                    }
                }
            }
        }
    }
}