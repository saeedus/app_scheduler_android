package com.meldcx.appscheduler.schedule.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.meldcx.appscheduler.schedule.data.model.ScheduledAlarm
import com.meldcx.appscheduler.schedule.presentation.ScheduleViewModel
import com.meldcx.appscheduler.schedule.presentation.UserAction
import com.meldcx.appscheduler.schedule.presentation.components.DateTimePickerDialog
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun EditScheduleScreen(
    modifier: Modifier = Modifier,
    viewModel: ScheduleViewModel,
    scheduleId: Int
) {
    val state by viewModel.state.collectAsState()
    var showDateTimePickerDialog by remember { mutableStateOf(false) }
    var selectedAlarm: ScheduledAlarm? by remember { mutableStateOf(null) }
    val context = LocalContext.current

    LaunchedEffect(scheduleId) {
        selectedAlarm = viewModel.getScheduledAlarmById(scheduleId)
        if (selectedAlarm != null) {
            showDateTimePickerDialog = true
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Edit Schedule", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        selectedAlarm?.let { alarm ->
            Text(
                text = "App: ${
                    context.packageManager.getApplicationLabel(
                        context.packageManager.getApplicationInfo(
                            alarm.packageName,
                            0
                        )
                    )
                }"
            )
            Text(
                text = "Current Time: ${
                    SimpleDateFormat(
                        "dd/MM/yyyy HH:mm",
                        Locale.getDefault()
                    ).format(
                        Calendar.getInstance().apply {
                            set(Calendar.YEAR, alarm.year); set(
                            Calendar.MONTH,
                            alarm.month
                        ); set(
                            Calendar.DAY_OF_MONTH,
                            alarm.day
                        ); set(Calendar.HOUR_OF_DAY, alarm.hour); set(Calendar.MINUTE, alarm.minute)
                        }.time
                    )
                }"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { showDateTimePickerDialog = true }) {
                Text(text = "Change Schedule Time")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewModel.onAction(UserAction.CancelSchedule(alarm)) }) {
                Text(text = "Cancel Schedule")
            }

            if (showDateTimePickerDialog) {
                DateTimePickerDialog(
                    onCancel = { showDateTimePickerDialog = false },
                    onConfirm = { year, month, day, hour, minute ->
                        showDateTimePickerDialog = false
                        val updatedAlarm = alarm.copy(
                            year = year,
                            month = month,
                            day = day,
                            hour = hour,
                            minute = minute
                        )
                        viewModel.onAction(UserAction.UpdateSchedule(updatedAlarm))
                    }
                )
            }
        }?: run {
            Text(text = "Schedule not found.")
        }
    }
}