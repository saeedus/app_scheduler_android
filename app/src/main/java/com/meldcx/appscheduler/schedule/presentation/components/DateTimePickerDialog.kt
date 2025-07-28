package com.meldcx.appscheduler.schedule.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerDialog(
    onCancel: () -> Unit,
    onConfirm: (year: Int, month: Int, day: Int, hour: Int, minute: Int) -> Unit,
) {
    val context = LocalContext.current
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= today
            }
        }
    )
    val timePickerState = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onCancel) {
        Surface {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!showTimePicker) {
                    DatePicker(state = datePickerState)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = onCancel) {
                            Text(text = "Cancel")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { showTimePicker = true }) {
                            Text(text = "Next")
                        }
                    }
                } else {
                    TimePicker(state = timePickerState)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(onClick = { showTimePicker = false }) {
                            Text(text = "Back")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            val calendar = Calendar.getInstance()
                            calendar.timeInMillis = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
                            val year = calendar.get(Calendar.YEAR)
                            val month = calendar.get(Calendar.MONTH)
                            val day = calendar.get(Calendar.DAY_OF_MONTH)
                            val hour = timePickerState.hour
                            val minute = timePickerState.minute

                            val selectedDateTime = Calendar.getInstance().apply {
                                set(year, month, day, hour, minute, 0)
                                set(Calendar.MILLISECOND, 0)
                            }

                            if (selectedDateTime.timeInMillis < System.currentTimeMillis()) {
                                Toast.makeText(context, "Cannot select a past time", Toast.LENGTH_SHORT).show()
                            } else {
                                onConfirm(year, month, day, hour, minute)
                            }
                        }) {
                            Text(text = "Confirm")
                        }
                    }
                }
            }
        }
    }
}
