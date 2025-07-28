package com.meldcx.appscheduler.schedule.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
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
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "All Schedules",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.allScheduledAlarms) { alarm ->
                val calendar = Calendar.getInstance().apply {
                    set(Calendar.YEAR, alarm.year)
                    set(Calendar.MONTH, alarm.month)
                    set(Calendar.DAY_OF_MONTH, alarm.day)
                    set(Calendar.HOUR_OF_DAY, alarm.hour)
                    set(Calendar.MINUTE, alarm.minute)
                }
                val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = context.packageManager.getApplicationIcon(alarm.packageName),
                            contentDescription = alarm.packageName,
                            modifier = Modifier.size(48.dp)
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Column {
                            Text(
                                text = context.packageManager.getApplicationLabel(
                                    context.packageManager.getApplicationInfo(
                                        alarm.packageName,
                                        0
                                    )
                                ).toString(),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = dateFormat.format(calendar.time),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = if (alarm.isExecuted) Icons.Filled.CheckCircle else Icons.Filled.Schedule,
                            tint = if (alarm.isExecuted) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.4f
                            ),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}