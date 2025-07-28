package com.meldcx.appscheduler.schedule.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.meldcx.appscheduler.service.AlarmService
import com.meldcx.appscheduler.util.Constants

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        intent?.let {
            it.getStringExtra(Constants.PACKAGE_NAME_KEY)?.let{ packageName ->
                val serviceIntent = Intent(context, AlarmService::class.java).apply {
                    putExtra(Constants.PACKAGE_NAME_KEY, packageName)
                }
                context.startService(serviceIntent)
            }
        }
    }
}