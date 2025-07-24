/*
 * Created by Saeedus Salehin on 24/7/25, 6:49 PM.
 */

package com.meldcx.appscheduler.schedule.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class ScheduleViewModel: ViewModel() {

    private val _state = MutableStateFlow(ScheduleState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<ScheduleEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()
}