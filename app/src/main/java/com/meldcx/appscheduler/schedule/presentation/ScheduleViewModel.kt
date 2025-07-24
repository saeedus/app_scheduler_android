/*
 * Created by Saeedus Salehin on 24/7/25, 6:49 PM.
 */

package com.meldcx.appscheduler.schedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meldcx.appscheduler.schedule.domain.GetApps
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val getApps: GetApps
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
                viewModelScope.launch {
                    _uiEvent.send(ScheduleEvents.NavigateToScheduler)
                }
            }
        }
    }

    private fun loadApps() {
        viewModelScope.launch {
            _state.update { it.copy(apps = getApps()) }
        }
    }
}