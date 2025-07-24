/*
 * Created by Saeedus Salehin on 24/7/25, 6:49 PM.
 */

package com.meldcx.appscheduler.schedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.schedule.domain.AlarmScheduler
import com.meldcx.appscheduler.schedule.domain.GetApps
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.meldcx.appscheduler.schedule.data.dao.ScheduledAlarmDao
import com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm

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
                    alarmScheduler.schedule(app.packageName, action.timeInMillis)
                    viewModelScope.launch {
                        scheduledAlarmDao.insert(ScheduledAlarm(packageName = app.packageName, timeInMillis = action.timeInMillis))
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

    fun getAlarmScheduler(): AlarmScheduler {
        return alarmScheduler
    }
}