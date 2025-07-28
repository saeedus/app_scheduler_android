package com.meldcx.appscheduler.schedule.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.meldcx.appscheduler.schedule.presentation.ScheduleState
import com.meldcx.appscheduler.schedule.presentation.ScheduleViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun UpcomingScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel,
    state: ScheduleState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Upcoming Schedules", modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(state.upcomingAlarms) { alarm ->
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, alarm.year)
                    set(Calendar.MONTH, alarm.month)
                    set(Calendar.DAY_OF_MONTH, alarm.day)
                    set(Calendar.HOUR_OF_DAY, alarm.hour)
                    set(Calendar.MINUTE, alarm.minute)
                }
                val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                Text(text = "${alarm.packageName} - ${dateFormat.format(calendar.time)}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Executed Schedules", modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(state.executedAlarms) { alarm ->
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, alarm.year)
                    set(Calendar.MONTH, alarm.month)
                    set(Calendar.DAY_OF_MONTH, alarm.day)
                    set(Calendar.HOUR_OF_DAY, alarm.hour)
                    set(Calendar.MINUTE, alarm.minute)
                }
                val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                Text(text = "${alarm.packageName} - ${dateFormat.format(calendar.time)}")
            }
        }
    }
}