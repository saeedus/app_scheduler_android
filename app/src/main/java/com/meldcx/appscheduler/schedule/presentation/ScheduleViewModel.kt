/*
 * Created by Saeedus Salehin on 24/7/25, 6:49 PM.
 */

package com.meldcx.appscheduler.schedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.schedule.data.dao.ScheduledAlarmDao
import com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm
import com.meldcx.appscheduler.schedule.domain.AlarmScheduler
import com.meldcx.appscheduler.schedule.domain.GetApps
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getApps: GetApps,
    private val alarmScheduler: AlarmScheduler,
    private val scheduledAlarmDao: ScheduledAlarmDao
) : ViewModel() {

    private val _state = MutableStateFlow(ScheduleState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<ScheduleEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadApps()
        loadScheduledAlarms()
    }

    fun onAction(action: UserAction) {
        when (action) {
            UserAction.LoadApps -> loadApps()

            is UserAction.AppSelected -> {
                _state.update { it.copy(selectedApp = action.appInfo) }
            }

            is UserAction.Schedule -> {
                val app = state.value.selectedApp
                if (app != null) {
                    val scheduledAlarm = ScheduledAlarm(
                        packageName = app.packageName,
                        year = action.year,
                        month = action.month,
                        day = action.day,
                        hour = action.hour,
                        minute = action.minute
                    )
                    viewModelScope.launch {
                        val newId = scheduledAlarmDao.insert(scheduledAlarm)
                        val alarmWithId = scheduledAlarm.copy(id = newId.toInt())
                        alarmScheduler.schedule(alarmWithId)
                        loadScheduledAlarms() // Reload alarms after scheduling
                    }
                }
            }
        }
    }

    private fun loadApps() {
        viewModelScope.launch {
            _state.update { it.copy(apps = getApps()) }
        }
    }

    private fun loadScheduledAlarms() {
        viewModelScope.launch {
            val allAlarms = scheduledAlarmDao.getAll()
            val upcoming = mutableListOf<ScheduledAlarm>()
            val executed = mutableListOf<ScheduledAlarm>()

            val currentTime = System.currentTimeMillis()

            allAlarms.forEach { alarm ->
                val calendar = java.util.Calendar.getInstance().apply {
                    set(java.util.Calendar.YEAR, alarm.year)
                    set(java.util.Calendar.MONTH, alarm.month)
                    set(java.util.Calendar.DAY_OF_MONTH, alarm.day)
                    set(java.util.Calendar.HOUR_OF_DAY, alarm.hour)
                    set(java.util.Calendar.MINUTE, alarm.minute)
                    set(java.util.Calendar.SECOND, 0)
                    set(java.util.Calendar.MILLISECOND, 0)
                }

                if (alarm.isExecuted || calendar.timeInMillis < currentTime) {
                    executed.add(alarm)
                } else {
                    upcoming.add(alarm)
                }
            }
            _state.update { it.copy(upcomingAlarms = upcoming, executedAlarms = executed) }
        }
    }
}