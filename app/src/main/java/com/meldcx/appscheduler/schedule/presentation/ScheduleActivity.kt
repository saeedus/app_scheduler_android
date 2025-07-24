package com.meldcx.appscheduler.schedule.presentation

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.meldcx.appscheduler.schedule.presentation.screens.AppsScreen
import com.meldcx.appscheduler.schedule.presentation.screens.PermissionScreen
import org.koin.androidx.compose.koinViewModel

class ScheduleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = koinViewModel<ScheduleViewModel>()
            val state by viewModel.state.collectAsState()

            if (hasExactAlarmPermission()) {
                AppsScreen(viewModel = viewModel, state = state)
            } else {
                PermissionScreen(onGrantPermissionClick = ::openAppSettings)
            }
        }
    }

    private fun hasExactAlarmPermission(): Boolean {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmManager.canScheduleExactAlarms()
        } else {
            true
        }
    }

    private fun openAppSettings() {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
            data = Uri.fromParts("package", packageName, null)
        }
        startActivity(intent)
    }
}